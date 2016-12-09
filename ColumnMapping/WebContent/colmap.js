$(function()
	{
		$("#sel").change(function()
		{
			if($(this).val()=="tobe")
			{
				$("#s_tablename").val("");
				$("#s_colname").val("");
				$(".asis").hide();
				$(".tobe").show();
			}
			
			if($(this).val()=="asis")
			{
				$("#t_tablename").val("");
				$("#t_colname").val("");
				$(".tobe").hide();
				$(".asis").show();
			}
		});
		
		$("#viewtable table td").dblclick(function()
		{
			var browser=navigator.userAgent.toLowerCase();
			
			if((browser.indexOf("trident")!=-1 && navigator.appName=="Netcsape") || browser.indexOf("msie")!=-1)
			{
				alert("its ie")
				window.clipboardData.setData("text", $(this).html());
				$("#alertlayer").html("다음을 복사했습니다: "+$(this).html());
				$("#alertlayer").show();
				$("#alertlayer").fadeOut(1800);
				
			}else
			{
				window.prompt("Ctrl+C로 복사하세요", $(this).html());
				$("#alertlayer").html("다음을 복사했습니다: "+$(this).html());
				$("#alertlayer").show();
				$("#alertlayer").fadeOut(1800);
			}
			
		});
		
						
	});
	
	function validate(cp)
	{
		var sel=document.getElementById("sel").value;
		var t_tab=document.getElementById("t_tablename").value;
		var t_col=document.getElementById("t_colname").value;
		var s_tab=document.getElementById("s_tablename").value;
		var s_col=document.getElementById("s_colname").value;
		
		var url=cp+"/colmap.jsp?r=y&sel="+sel;
		
		if(t_tab!="" && t_tab!=null)
			url+="&t_tablename="+t_tab;
		
		if(t_col!="" && t_col!=null)
			url+="&t_colname="+t_col;
		
		if(s_tab!="" && s_tab!=null)
			url+="&s_tablename="+s_tab;
		
		if(s_col!="" && s_col!=null)
			url+="&s_colname="+s_col;
		
		location.href=url;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	