(function($){
   /*var trace = function(msg){
     if (typeof(window)=='undefined' || !window.console) return;
     var len = arguments.length, args = [];
    for (var i=0; i<len; i++) args.push("arguments["+i+"]");
     eval("console.log("+args.join(",")+")");
   } ;*/ 
  
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

        //if (document.referrer.match(/echolalia|atlas|halfviz/)){
          // if we got here by hitting the back button in one of the demos, 
          // start with the demos section pre-selected
         // that.switchSection('个人健康');
        //}
      },
      resize:function(){
        /*canvas.width = $(window).width();
        canvas.height = .75* $(window).height();*/
    	canvas.width=.5*$(window).width();
    	canvas.height=.5*$(window).height();
        sys.screen({size:{width:canvas.width, height:canvas.height}});
        _vignette = null;
        that.redraw();
      },
      redraw:function(){
        gfx.clear();
        sys.eachEdge(function(edge, p1, p2){
          if (edge.source.data.alpha * edge.target.data.alpha == 0) return;
          //if(edge.target.data.alpha==0) return;
          //gfx.line(p1,p2,{stroke:"#b2b19d",width:2,alpha:edge.source.data.alpha});
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
    			/*var node1=sys.getNode("收支");
    			node.p.x = secNode.p.x + .05*Math.random() - .025;
                node.p.y = parent.p.y + .05*Math.random() - .025;
                node.tempMass = .001;*/
    			sys.tweenNode("收支", 0.5, {alpha:1});
    			sys.tweenNode("保管箱", 0.5, {alpha:1});
    			sys.tweenNode("健康", 0.5, {alpha:1});
    			sys.tweenNode("财产", 0.5, {alpha:1});
    			sys.tweenNode("朋友", 0.5, {alpha:1});
    			sys.tweenNode("日志", 0.5, {alpha:1});
    			sys.tweenNode("宠物", 0.5, {alpha:1});
    			sys.tweenNode("私人订制", 0.5, {alpha:1});
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
    	} 
       /* var parent = sys.getEdgesFrom(newSection)[0].source;
        //console.log(parent);
        var children = $.map(sys.getEdgesFrom(newSection), function(edge){
          return edge.target;
        });
        
        sys.eachNode(function(node){
         //if (node.data.shape=='dot') return ;// skip all but leafnodes

          var nowVisible = ($.inArray(node, children)>=0 );
         var nowVisible = ($.inArray(node, children)>=0 || node==parent);
          var newAlpha = (nowVisible) ? 1 : 0;
          var dt = (nowVisible) ? .5 : .5;
          sys.tweenNode(node, dt, {alpha:newAlpha});
          if (newAlpha==1){
            node.p.x = parent.p.x + .05*Math.random() - .025;
            node.p.y = parent.p.y + .05*Math.random() - .025;
            node.tempMass = .001;
          }
        });
        */
      },
      
      
      _initMouseHandling:function(){
        // no-nonsense drag and drop (thanks springy.js)
    	
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
              if (selected){
                 dom.addClass('linkable');
                 window.status = selected.node.data.link.replace(/^\//,"http://"+window.location.host+"/").replace(/^#/,'');
              }
              else{
                 /*dom.removeClass('linkable');*/
            	  dom.addClass('linkable');
                 window.status = '';
              }
            }else if ($.inArray(nearest.node.name, ['数据银行','收支','保管箱','健康','支出','收入','体检','病历','保健',
                                                    '财产','实物资产','保险','投资','朋友','朋友圈','通话','短信','日志',
                                                    '计划','成果','网页浏览','位置','宠物','私人订制','教育','成长','课程']) >=0 ){
              if (nearest.node.name!=_section){
            	 dom.addClass('linkable');
                _section = nearest.node.name;
                that.switchSection(_section);
              }
              //dom.removeClass('linkable');
              //window.status = '';
            }
            
            return false;
          },
          clicked:function(e){
        	console.log("cilcked!!!!"); 
            var pos = $(canvas).offset();
            _mouseP = arbor.Point(e.pageX-pos.left, e.pageY-pos.top);
            nearest = dragged = sys.nearest(_mouseP);
            console.log(nearest.node.name);
            var askURL=null;
            if(nearest.node.data.shape=='dot'){
            	//保管箱_id=5，宠物_id=23，私人订制_id=24.
            	if(nearest.node._id==5||nearest.node._id==23||nearest.node._id==24){
            		switch(nearest.node._id){
            			case 5:
            				askURL='../../../../../rest/network/safebox/getSafeboxByUserId';
            				break;
            			case 23:
            				askURL='../../../../../rest/common/pet/page';
            				break;
            			case 24:
            				askURL='../../../../../rest/common/cdr/page';
            				break;
            		}
            	}else{
            		var cNode = $.map(sys.getEdgesFrom(nearest.node), function(edge){
                        return edge.target;
                      });
                	if(cNode.length>0){
                		for(var n=0;n<cNode.length;n++){
                			console.log(cNode[n].data.alpha);
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
            }else{
            		//收入_id=3，支出_id=4，保健_id=7，病历_id=8，体检_id=9，
            	    //实物资产_id=11，保险_id=12，投资_id=13，朋友圈_id=15，通话_id=16，
            		//短信_id=17，计划_id=19，成果_id=20，网页浏览_id=21，位置_id=22，
            		//成长_id=26，课程_id=27.
            	switch(nearest.node._id){
	    			case 3:
	    				askURL='../../../../../rest/common/income/page';
	    				break;
	    			case 4:
	    				askURL='../../../../../rest/common/consume/page';
	    				break;
	    			case 7:
	    				askURL='../../../../../rest/common/ht/page';
	    				break;
	    			case 8:
	    				askURL='../../../../../rest/common/medh/page';
	    				break;
	    			case 9:
	    				askURL='../../../../../rest/common/pe/page';
	    				break;
	    			case 11:
	    				askURL='../../../../../rest/app/asset/page/phyass';
	    				break;
	    			case 12:
	    				askURL='../../../../../rest/app/asset/page/insu';
	    				break;
	    			case 13:
	    				askURL='../../../../../rest/app/asset/page/inve';
	    				break;
	    			case 15:
	    				askURL='../../../../../rest/app/contact/page';
	    				break;
	    			case 16:
	    				askURL='../../../../../rest/app/call/page';
	    				break;
	    			case 17:
	    				askURL='../../../../../rest/app/message/page';
	    				break;
	    			case 19:
	    				askURL='../../../../../rest/common/plan/page';
	    				break;
	    			case 20:
	    				askURL='../../../../../rest/app/ach/page';
	    				break;
	    			case 21:
	    				askURL='../../../../../rest/common/brow/page';
	    				break;
	    			case 22:
	    				askURL='../../../../../rest/common/location/page';
	    				break;
	    			case 26:
	    				askURL='../../../../../rest/common/grow/page';
	    				break;
	    			case 27:
	    				askURL='../../../../../rest/common/course/page';
	    				break;
    		}  
           }
            	
             /* var link = selected.node.data.link;
              if (link.match(/^#/)){
                 $(that).trigger({type:"navigate", path:link.substr(1)});
              }else{
                 window.location = link;
              }
              return false;*/
           // }
            
            
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
      branch3:"#922E00",
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
	              "通话":{color:CLR.branch4,alpha:0},//16
	              "短信":{color:CLR.branch4,alpha:0},//17
	         "日志":{color:CLR.branch2,shape:"dot",alpha:0},//18
	              "计划":{color:CLR.branch4,alpha:0},//19
	              "成果":{color:CLR.branch4,alpha:0},//20
	              "网页浏览":{color:CLR.branch4,alpha:0},//21
	              "位置":{color:CLR.branch4,alpha:0},//22
	        "宠物":{color:CLR.branch2,shape:"dot",alpha:0},//23
	        "私人订制":{color:CLR.branch2,shape:"dot",alpha:0},//24
	        "教育":{color:CLR.branch2,shape:"dot",alpha:0},//25
	              "成长":{color:CLR.branch4, alpha:0},//26
	              "课程":{color:CLR.branch4, alpha:0}//27
            },
      edges:{
        "数据银行":{
        	"收支":{length:.1},
        	"保管箱":{length:.1},
        	"健康":{length:.1},
        	"财产":{length:.1},
        	"朋友":{length:.1},
        	"日志":{length:.1},
        	"宠物":{length:.1},
        	"私人订制":{length:.1},
        	"教育":{length:.1}
        },
        "收支":{
        	"收入":{length:.1},
        	"支出":{length:.1}
	    },
	    
	    "健康":{
	    	"保健":{length:.1},
	    	"病历":{length:.1},
	    	"体检":{length:.1}
	    },
	    "财产":{
	    	"实物资产":{length:.1},
            "保险":{length:.1},
            "投资":{length:.1}
	    },
	    "朋友":{
	    	"朋友圈":{length:.1},
            "通话":{length:.1},
            "短信":{length:.1}
	    },
	    "日志":{
	    	"计划":{length:.1},
            "成果":{length:.1},
            "网页浏览":{length:.1},
            "位置":{length:.1}
	    },
	    "教育":{
	    	"成长":{length:.1},
            "课程":{length:.1}
	    }
      }
    };


    var sys = arbor.ParticleSystem();
    sys.parameters({stiffness:1000, repulsion:500,friction:0.4, gravity:true, dt:0.015});
    sys.renderer = Renderer("#sitemap");
    sys.graft(theUI);
    
    /*var nav = Nav("#nav");
    $(sys.renderer).bind('navigate', nav.navigate);
    $(nav).bind('mode', sys.renderer.switchMode);
    nav.init();*/
  });
})(this.jQuery);