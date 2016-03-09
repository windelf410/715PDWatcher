(function($){  
  var Renderer = function(elt){
    var dom = $(elt);
    var canvas = dom.get(0);
    var ctx = canvas.getContext("2d");
    var gfx = arbor.Graphics(canvas);
    var sys = null;

    var _vignette = null;
    var selected = null;
    var nearest = null;
    var  _mouseP = null;

    
    var that = {
      init:function(pSystem){
        sys = pSystem;
        sys.screen({size:{width:dom.width(), height:dom.height()},
                    padding:[36,60,36,60]});

        $(window).resize(that.resize);
        that.resize();
        that._initMouseHandling();
      },
      resize:function(){
    	canvas.width=.95*$(window).width();
    	canvas.height=.95*$(window).height();
        sys.screen({size:{width:canvas.width, height:canvas.height}});
        _vignette = null;
        that.redraw();
      },
      redraw:function(){
        gfx.clear();
        sys.eachEdge(function(edge, p1, p2){
          if (edge.source.data.alpha * edge.target.data.alpha == 0) return;
          gfx.line(p1, p2, {stroke:"#b2b19d", width:2, alpha:edge.target.data.alpha});
        });
        sys.eachNode(function(node, pt){
          var w = Math.max(20, 20+gfx.textWidth(node.name) );
          if (node.data.alpha==0) return;
          if (node.data.shape=='dot'){
            gfx.oval(pt.x-w/2, pt.y-w/2, w, w, {fill:node.data.color, alpha:node.data.alpha});
            gfx.text(node.name, pt.x, pt.y+7, {color:"white", align:"center", font:"Arial", size:12});
          }else{
            gfx.rect(pt.x-w/2, pt.y-8, w, 20, 4, {fill:node.data.color, alpha:node.data.alpha});
            gfx.text(node.name, pt.x, pt.y+9, {color:"white", align:"center", font:"Arial", size:12});
          }
        });
      },
      switchSection:function(newSection){
    	var secNode=sys.getNode(newSection);
    	if(secNode.data.shape=='dot'){
    		var id=secNode._id;
    		var alpha=secNode.data.alpha;
    		if(id==1 && alpha==1){
    			sys.tweenNode("收支", 0.5, {alpha:1});
    			/*sys.tweenNode("保管箱", 0.5, {alpha:1});*/
    			sys.tweenNode("健康", 0.5, {alpha:1});
    			sys.tweenNode("财产", 0.5, {alpha:1});
    			sys.tweenNode("朋友", 0.5, {alpha:1});
    			sys.tweenNode("日志", 0.5, {alpha:1});
    			sys.tweenNode("宠物", 0.5, {alpha:1});
    			/*sys.tweenNode("私人订制", 0.5, {alpha:1});*/
    			sys.tweenNode("教育", 0.5, {alpha:1});
    		}
    		if(id==2 && alpha==1){
    			sys.tweenNode("收入", 0.5, {alpha:1});
    			sys.tweenNode("支出", 0.5, {alpha:1});
    		}
    		if(id==6 && alpha==1){
    			sys.tweenNode("病历", 0.5, {alpha:1});
    			sys.tweenNode("体检", 0.5, {alpha:1});
    			sys.tweenNode("保健", 0.5, {alpha:1});
    		}
    		if(id==10 && alpha==1){
    			sys.tweenNode("实物资产", 0.5, {alpha:1});
    			sys.tweenNode("保险", 0.5, {alpha:1});
    			sys.tweenNode("投资", 0.5, {alpha:1});
    		}
    		if(id==14 && alpha==1){
    			sys.tweenNode("朋友圈", 0.5, {alpha:1});
    			sys.tweenNode("通话", 0.5, {alpha:1});
    			sys.tweenNode("短信", 0.5, {alpha:1});
    		}
    		if(id==18 && alpha==1){
    			sys.tweenNode("计划", 0.5, {alpha:1});
    			sys.tweenNode("成果", 0.5, {alpha:1});
    			sys.tweenNode("网页浏览", 0.5, {alpha:1});
    			sys.tweenNode("位置", 0.5, {alpha:1});
    		}
    		if(id==25 && alpha==1){
    			sys.tweenNode("成长", 0.5, {alpha:1});
    			sys.tweenNode("课程", 0.5, {alpha:1});
    		}
    		
    		if(id==16 && alpha==1){
    			sys.tweenNode("来电", 0.5, {alpha:1});
    			sys.tweenNode("去电", 0.5, {alpha:1});
    			/*sys.tweenNode("网页浏览", 0.5, {alpha:1});
    			sys.tweenNode("位置", 0.5, {alpha:1});*/
    		}
    		if(id==17 && alpha==1){
    			sys.tweenNode("收信", 0.5, {alpha:1});
    			sys.tweenNode("发信", 0.5, {alpha:1});
    			/*sys.tweenNode("网页浏览", 0.5, {alpha:1});
    			sys.tweenNode("位置", 0.5, {alpha:1});*/
    		}
    	} 
      },
      
      
      _initMouseHandling:function(){
        selected = null;
        nearest = null;
        var dragged = null;
        var oldmass = 1;

        var _section = null;
        var handler = {
          //鼠标移动到此节点时的动作
          moved:function(e){
            var pos = $(canvas).offset();
            _mouseP = arbor.Point(e.pageX-pos.left, e.pageY-pos.top);
            nearest = sys.nearest(_mouseP);

            if (!nearest.node) return false;
            
            if (nearest.node.data.shape!='dot'){
              selected = (nearest.distance <5) ? nearest : null;
            }else 
            if ($.inArray(nearest.node.name, ['数据银行','收支','保管箱','健康','支出','收入','体检','病历','保健',
                                                    '财产','实物资产','保险','投资','朋友','朋友圈','通话','短信','日志',
                                                    '计划','成果','网页浏览','位置','宠物','私人订制','教育','成长','课程','通话','短信']) >=0 ){
              if (nearest.node.name!=_section){
                 _section = nearest.node.name;
                 that.switchSection(_section);
              }
            }
            
            return false;
          },
          clicked:function(e){
            var pos = $(canvas).offset();
            _mouseP = arbor.Point(e.pageX-pos.left, e.pageY-pos.top);
            nearest = dragged = sys.nearest(_mouseP);
            
            var chartURL=null;
            var tableURL=null;
            if(nearest.node.data.shape=='dot'&&nearest.node.data.alpha==1){
            	//保管箱_id=5，宠物_id=23，私人订制_id=24.
            	if(nearest.node._id==5||nearest.node._id==23||nearest.node._id==24){
            		switch(nearest.node._id){
            			case 5:
            				
            				chartURL='../rest/network/safebox/getSafeboxByUserId';
            				tableURL='../rest/network/safebox/getSafeboxByUserId';
            				window.parent.creat(chartURL,tableURL);
            				return false;
            				//break;
            			case 23:
            				chartURL='../rest/common/animal/browse';
            				tableURL='../rest/common/animal/page';
            				window.parent.creat(chartURL,tableURL);
            				return false;
            				//break;
            			case 24:
            				chartURL='../rest/common/cdr/browse';
            				tableURL='../rest/common/cdr/page';
            				window.parent.creat(chartURL,tableURL);
    	    	            return false;
            				//break;
            		}
            	}else{
            		var cNode = $.map(sys.getEdgesFrom(nearest.node), function(edge){
                        return edge.target;
                      });
                	if(cNode.length>0){
                		for(var n=0;n<cNode.length;n++){
                			//console.log(cNode[n].data.alpha);
                			if(cNode[n].data.alpha==1){
                				sys.tweenNode(cNode[n],0.5,{alpha:0});
                				var ccNode = $.map(sys.getEdgesFrom(cNode[n]), function(edge){
                                    return edge.target;
                                  });
                				if(ccNode.length>0){
                					for(var m=0;m<ccNode.length;m++){
                						sys.tweenNode(ccNode[m],0.5,{alpha:0});
                						var cccNode = $.map(sys.getEdgesFrom(ccNode[m]), function(edge){
                                            return edge.target;
                                          });
                						if(cccNode.length>0){
                							for(var p=0;p<cccNode.length;p++){
                								sys.tweenNode(cccNode[p],0.5,{alpha:0});
                							}
                						}
                					}
                				}
                				
                			}else{
                				sys.tweenNode(cNode[n],0.5,{alpha:1});
                			}
                			
                		}
                	}
            	}
            }else if(nearest.node.data.alpha==1){
            		//收入_id=3，支出_id=4，保健_id=7，病历_id=8，体检_id=9，
            	    //实物资产_id=11，保险_id=12，投资_id=13，朋友圈_id=15，通话_id=16，
            		//短信_id=17，计划_id=19，成果_id=20，网页浏览_id=21，位置_id=22，
            		//成长_id=26，课程_id=27.
            	    //来电_id=28，去电_id=29.收信_id=30，发信_id=31.
            	switch(nearest.node._id){
	    			case 3:
	    				chartURL='../rest/common/income/browse';
	    				tableURL='../rest/common/income/page';
	    				window.parent.creat(chartURL,tableURL);
	    	            return false;
	    				//break;
	    			case 4:
	    				chartURL='../rest/common/consume/browse';
	    				tableURL='../rest/common/consume/page';
	    				window.parent.creat(chartURL,tableURL);
	    				return false;
	    				//break;
	    			case 7:
	    				chartURL='../rest/common/ht/browse';
	    				tableURL='../rest/common/ht/page';
	    				window.parent.creat(chartURL,tableURL);
	    				return false;
	    				//break;
	    			case 8:
	    				chartURL='../rest/common/medh/browse';
	    				tableURL='../rest/common/medh/page';
	    				window.parent.creat(chartURL,tableURL);
	    				return false;
	    				//break;
	    			case 9:
	    				chartURL='../rest/common/pe/browse';
	    				tableURL='../rest/common/pe/page';
	    				window.parent.creat(chartURL,tableURL);
	    				return false;
	    				//break;
	    			case 11:
	    				chartURL='../rest/app/asset/browse/phyass';
	    				tableURL='../rest/app/asset/page/phyass';
	    				window.parent.creat(chartURL,tableURL);
	    				return false;
	    				//break;
	    			case 12:
	    				chartURL='../rest/app/asset/browse/insu';
	    				tableURL='../rest/app/asset/page/insu';
	    				window.parent.creat(chartURL,tableURL);
	    				return false;
	    				//break;
	    			case 13:
	    				chartURL='../rest/app/asset/browse/inve';
	    				tableURL='../rest/app/asset/page/inve';
	    				window.parent.creat(chartURL,tableURL);
	    				return false;
	    				//break;
	    			case 15:
	    				chartURL='../rest/app/contact/browse';
	    				tableURL='../rest/app/contact/page';
	    				window.parent.creat(chartURL,tableURL);
	    				return false;
	    				//break;
	    			case 19:
	    				chartURL='../rest/common/plan/browse';
	    				tableURL='../rest/common/plan/page';
	    				window.parent.creat(chartURL,tableURL);
	    				return false;
	    				//break;
	    			case 20:
	    				chartURL='../rest/app/ach/browse';
	    				tableURL='../rest/app/ach/page';
	    				window.parent.creat(chartURL,tableURL);
	    				return false;
	    				//break;
	    			case 21:
	    				chartURL='../rest/common/brow/browse';
	    				tableURL='../rest/common/brow/page';
	    				window.parent.creat(chartURL,tableURL);
	    				return false;
	    				//break;
	    			case 22:
	    				chartURL='../rest/common/location/browse';
	    				tableURL='../rest/common/location/page';
	    				window.parent.creat(chartURL,tableURL);
	    				return false;
	    				//break;
	    			case 26:
	    				chartURL='../rest/common/grow/browse';
	    				tableURL='../rest/common/grow/page';
	    				window.parent.creat(chartURL,tableURL);
	    				return false;
	    				//break;
	    			case 27:
	    				chartURL='../rest/common/course/browse';
	    				tableURL='../rest/common/course/page';
	    				window.parent.creat(chartURL,tableURL);
	    				return false;
	    				//break;
	    			case 28:
	    				chartURL='../rest/app/call/browseR';
	    				tableURL='../rest/app/call/pageR';
	    				window.parent.creat(chartURL,tableURL);
	    				return false;
	    				//break;
	    			case 29:
	    				chartURL='../rest/app/call/browseS';
	    				tableURL='../rest/app/call/pageS';
	    				window.parent.creat(chartURL,tableURL);
	    				return false;
	    				//break;
	    			case 30:
	    				chartURL='../rest/app/message/browseR';
	    				tableURL='../rest/app/message/pageR';
	    				window.parent.creat(chartURL,tableURL);
	    				return false;
	    				//break;
	    			case 31:
	    				chartURL='../rest/app/message/browseS';
	    				tableURL='../rest/app/message/pageS';
	    				window.parent.creat(chartURL,tableURL);
	    				return false;
	    				//break;
    		}  
           }
            if (dragged && dragged.node !== null) dragged.node.fixed = true;
            $(canvas).unbind('mousemove', handler.moved);
            $(canvas).bind('mousemove', handler.dragged);
            $(window).bind('mouseup', handler.dropped);
            return false;
          },
          dragged:function(e){
            var old_nearest = nearest && nearest.node._id;
            var pos = $(canvas).offset();
            var s = arbor.Point(e.pageX-pos.left, e.pageY-pos.top);

            if (!nearest) return;
            if (dragged !== null && dragged.node !== null){
              var p = sys.fromScreen(s);
              dragged.node.p = p;
            }

            return false;
          },

          dropped:function(e){
            if (dragged===null || dragged.node===undefined) return;
            if (dragged.node !== null) dragged.node.fixed = false;
            dragged.node.tempMass = 1000;
            dragged = null;
            // selected = null
            $(canvas).unbind('mousemove', handler.dragged);
            $(window).unbind('mouseup', handler.dropped);
            $(canvas).bind('mousemove', handler.moved);
            _mouseP = null;
            return false;
          }


        };

        $(canvas).mousedown(handler.clicked);
        $(canvas).mousemove(handler.moved);

      }
    };
    
    return that;
  };
  $(document).ready(function(){
		
    var CLR = {
      branch1:"#b2b19d",
      branch2:"orange",
      branch4:"#a7af00"
    };

    var theUI = {
      nodes:{"数据银行":{color:"red", shape:"dot", alpha:1},//1
    	  
             "收支":{color:CLR.branch2,shape:"dot",alpha:0},//2
	              "收入":{color:CLR.branch4,alpha:0},//3
	              "支出":{color:CLR.branch4,alpha:0},//4
             "保管箱":{color:CLR.branch2,shape:"dot",alpha:0},//5             
             "健康":{color:CLR.branch2,shape:"dot",alpha:0},//6
	              "保健":{color:CLR.branch4, alpha:0},//7
	              "病历":{color:CLR.branch4,alpha:0},//8
	              "体检":{color:CLR.branch4,alpha:0},//9
             "财产":{color:CLR.branch2,shape:"dot",alpha:0},//10
	              "实物资产":{color:CLR.branch4,alpha:0},//11
	              "保险":{color:CLR.branch4,alpha:0},//12
	              "投资":{color:CLR.branch4,alpha:0},//13
	         "朋友":{color:CLR.branch2,shape:"dot",alpha:0},//14
	              "朋友圈":{color:CLR.branch4,alpha:0},//15
	              //"通话":{color:CLR.branch4,alpha:0},//16
	              "通话":{color:CLR.branch2,shape:"dot",alpha:0},
	              //"短信":{color:CLR.branch4,alpha:0},//17
	              "短信":{color:CLR.branch2,shape:"dot",alpha:0},
	         "日志":{color:CLR.branch2,shape:"dot",alpha:0},//18
	              "计划":{color:CLR.branch4,alpha:0},//19
	              "成果":{color:CLR.branch4,alpha:0},//20
	              "网页浏览":{color:CLR.branch4,alpha:0},//21
	              "位置":{color:CLR.branch4,alpha:0},//22
	        "宠物":{color:CLR.branch2,shape:"dot",alpha:0},//23
	        "私人订制":{color:CLR.branch2,shape:"dot",alpha:0},//24
	        "教育":{color:CLR.branch2,shape:"dot",alpha:0},//25
	              "成长":{color:CLR.branch4, alpha:0},//26
	              "课程":{color:CLR.branch4, alpha:0},//27
	        "来电":{color:CLR.branch4,alpha:0},//28
	        "去电":{color:CLR.branch4,alpha:0},//29
	        "收信":{color:CLR.branch4,alpha:0},//30
	        "发信":{color:CLR.branch4,alpha:0},//31
            },
      edges:{
        "数据银行":{
        	"收支":{length:8},
        	/*"保管箱":{length:8},*/
        	"健康":{length:8},
        	"财产":{length:8},
        	"朋友":{length:8},
        	"日志":{length:8},
        	"宠物":{length:8},
        	/*"私人订制":{length:8},*/
        	"教育":{length:8}
        },
        "收支":{
        	"收入":{length:4},
        	"支出":{length:4}
	    },
	    
	    "健康":{
	    	"保健":{length:4},
	    	"病历":{length:4},
	    	"体检":{length:4}
	    },
	    "财产":{
	    	"实物资产":{length:4},
            "保险":{length:4},
            "投资":{length:4}
	    },
	    "朋友":{
	    	"朋友圈":{length:4},
            "通话":{length:4},
            "短信":{length:4}
	    },
	    "日志":{
	    	"计划":{length:4},
            "成果":{length:4},
            "网页浏览":{length:4},
            "位置":{length:4}
	    },
	    "教育":{
	    	"成长":{length:4},
            "课程":{length:4}
	    },
	    "通话":{
	    	"来电":{length:4},
            "去电":{length:4}
	    },
	    "短信":{
	    	"收信":{length:4},
            "发信":{length:4}
	    }
      }
    };


    var sys = arbor.ParticleSystem();
    sys.parameters({stiffness:900, repulsion:2000,friction:0.6, gravity:true, dt:0.015});
    sys.renderer = Renderer("#sitemap");
    sys.graft(theUI);
  });
})(this.jQuery);

/*******弹出窗口和统计模型的设置********/
function creat(){
	 parent.$('#win').window('open');
	 checkscreen();
	 parent.$('#layout').layout('expand','west');
}

function checkscreen()
{
	var width1=parent.$('#tab1').innerWidth();
	parent.window.document.getElementById('main').style.width = width1 + "px";
	parent.window.document.getElementById('main').style.height = parent.$('#tab1').innerHeight() + "px";
	parent.window.document.getElementById('chartShow').style.width = width1 + "px";
	parent.window.document.getElementById('chartShow').style.height = (parent.$('#win').innerHeight()-parent.$('#main').innerHeight()-6 -22-20) + "px";
}

var memberArray=new Array();
/* 得到memberName*/
function getMemberName(value,row,index){
	for(var i=0;i<memberArray.length;i++){
		if(value==memberArray[i].type_ID)
			return memberArray[i].typeName;
	}
}

/************饼状图的显示设置*************/
var option = {
		title : {
			subtext: '数据来自数汇宝',
			x:'center'
		},
		tooltip : {
			trigger: 'item',
			formatter: "{a} <br/>{b} : {c} ({d}%)"
		},
		legend:{
			orient:'vertical',
			x:'left',
		},
		calculable : true,
		toolbox: {
			show : true,
			feature : {
			dataView : {show: true, readOnly: false},
			magicType : {
               show: true, 
               type: ['pie', 'funnel'],
               option: {
                   funnel: {
                       x: '25%',
                       width: '50%',
                       funnelAlign: 'left',
                       max: 1548
                   }
               }
           },
			restore : {show: true},
			saveAsImage : {show: true}
			}
		},
		series : [
		 {
			 name:'收入组成',
			 type:'pie',
			radius : '55%',
			center: ['50%', '60%'], 
		  }
		]
};

/*************饼状图的标题和左上角显示的项数*******************/
window.optionText=null;
window.statisticsItem=null;

function getdata0(url,staClass)
{  
	/*************staClass表示统计类别，比如收入、支出、通话、短信等****************/
	switch(staClass){
	case 'income':
		window.optionText='收入组成';
		window.statisticsItem=['工资','投资收益','奖金','利息','中彩','其他'];
		break;
	case 'consume':
		window.optionText='收入组成';
		window.statisticsItem=['工资','投资收益','奖金','利息','中彩','其他'];
	    break;
	}
	var myChart = echarts.init(parent.window.document.getElementById('chartShow'));
	$.ajax({
		url: url,
		type: 'get',
		cache: false,
		dataType: 'json',
		beforeSend : function() {
           myChart.showLoading({
               text: '正在努力的读取数据中...',
           });
       },
		success: function(data){
				option.series[0].data = data; 
				option.legend.data=window.statisticsItem;
				option.title.text=window.optionText;
				myChart.setOption(option,true);
				myChart.hideLoading();
			},
		error: function(xhr, msg){
			alert(msg);
			}
		});
}
