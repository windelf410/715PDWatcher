function login(source){
		$.ajax({
			url: '../rest/app/user/login',
			type: 'post',
			dataType: 'text',
			async: false,
			data:{loginID: $('#name').val().trim(), password:$('#password').val().trim(), source: source},
			success: function(data){
				data = JSON.parse(data);
				try{
					if(data.loginID == $('#name').val().trim()){
						return true;
					}
					else if(data.code != undefined)
						alert(data.desc);
					else 
				}catch(err){
				}
			},
			complete: function(xhr, ts){
				if(JSON.parse(xhr.responseText).code)
					return false;
				else
					return true;
			}
		});
	}