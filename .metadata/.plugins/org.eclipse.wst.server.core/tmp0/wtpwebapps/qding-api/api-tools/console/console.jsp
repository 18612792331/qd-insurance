<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>千丁API接口调试系统 1.0</title>
<link rel="stylesheet" href="/qding-api/api-tools/console/lib/themes/default/style.min.css">
<link rel="stylesheet" href="/qding-api/api-tools/console/lib/themes/default-dark/style.min.css">
<link rel="stylesheet" href="/qding-api/api-tools/console/lib/themes/ui-dialog.css">
<style>
	pre {outline: 1px solid #ccc; padding: 5px; margin: 5px; }
	.string { color: green; }
	.number { color: darkorange; }
	.boolean { color: blue; }
	.null { color: magenta; }
	.key { color: red; }
	ol {
		counter-reset: li; /* Initiate a counter */
		list-style: none; /* Remove default numbering */
		*list-style: decimal; /* Keep using default numbering for IE6/7 */
		font: 15px 'trebuchet MS', 'lucida sans';
		padding: 0;
		margin-bottom: 4em;
		text-shadow: 0 1px 0 rgba(255,255,255,.5);
	}

	ol ol {
		margin: 0 0 0 2em; /* Add some left margin for inner lists */
	}
	.rounded-list a{ position: relative; display: block; padding: .4em .4em .4em 2em; *padding: .4em; margin: .5em 0; background: #ddd; color: #444; text-decoration: none; border-radius: .3em; transition: all .3s ease-out; } .rounded-list a:hover{ background: #eee; } .rounded-list a:hover:before{ transform: rotate(360deg); } .rounded-list a:before{ content: counter(li); counter-increment: li; position: absolute; left: -1.3em; top: 50%; margin-top: -1.3em; background: #87ceeb; height: 2em; width: 2em; line-height: 2em; border: .3em solid #fff; text-align: center; font-weight: bold; border-radius: 2em; transition: all .3s ease-out; }
</style>
</head>
<body style="background:#999;">
	<table style="width:100%">
		<thead>
			<th style="width:10%;background-color:rgb(217,39,49);color:white;margin-right: 20px;">服务<input type="text" id="search"/></th>
			<th style="width:50%;background-color:rgb(217,39,49);color:white;height:40px">描述</th>
			<th style="width:40%;background-color:rgb(217,39,49);color:white;height:40px">调试</th>
			
		</thead>
		<tbody>
			<tr>
				<td id="tree" style="color:#999;vertical-align:top;padding:20px;"></td>
				<td id="doc" class="jstree jstree-1 jstree-default-dark" style="color:#999;vertical-align:top;padding:20px;">
					<table>
						<tr><td>总接口数量：</td>
							<td id="methodCount"></td></tr>
						<tr><td colspan="2" id="vMethodCount"></td></tr>
					</table>

				</td>
				<td class="jstree jstree-1 jstree-default-dark" style="color:#999;vertical-align:top;padding:20px;">
					<form id="debugForm">
						<table>
							<%--<tr style=" margin-top: 20px;"><td style="width: 30%">运行环境</td><td style="width: 70%"><select id="runType"  style="width: 150px;height:30px; "><option value="local">本机环境</option><option value="dev">开发环境</option><option value="qa">测试环境</option></select></td></tr>
							--%><tr style=" margin-top: 20px;"><td>数据格式</td><td><select id="protocolAlias" style="width: 150px;height:30px; "><option value="json">json</option><option value="xml">xml</option></select></td></tr>
							<tr style=" margin-top: 20px;"><td>请求类型</td><td><select id="method" style="width: 150px;height:30px; "><option value="get">get</option><option value="post">post</option></select></td></tr>
                            <tr style=" margin-top: 20px;"><td>请求参数</td><td></td></tr>
							<tr style=" margin-top: 20px;">
								<td colspan="2">
									<textarea style=" width: 410px; height: 300px;" id="queryString"></textarea>
								</td>
							</tr>
							<tr style=" margin-top: 20px;">
								<td colspan="2">
									<input type="button" style="width: 100px; text-align:center; margin-top: 20px;margin-right: 20px;" value="提交请求" id="debug"/>
								 	<input type="button" value="查看用例"  style="width: 100px; text-align:center; margin-top: 20px;" id="example">
								 	<textarea id="testQuery" style="display: none"></textarea>
								 	<textarea id="testReuqest"  style="display: none"></textarea>
								 	<input type="hidden" id="exampleId"/> 
								</td>
							</tr>
							<tr style=" margin-top: 20px;"><td>Mock Response</td><td></td></tr>
							<tr style=" margin-top: 20px;">
								<td colspan="2">
									<textarea style=" width: 410px; height: 600px;" id="mockData">mockData</textarea>
								</td>
							</tr>

						</table>
					</form>
					
				</td>
			</tr>
		</tbody>
	</table>
	<div id="vMethodHtml" style="display:none"></div>
	<script type="text/javascript" src="/qding-api/api-tools/console/lib/jquery.js"></script>
	<script type="text/javascript" src="/qding-api/api-tools/console/lib/jstree.min.js"></script>
	<script type="text/javascript" src="/qding-api/api-tools/console/lib/json2.js"></script>
	<script  type="text/javascript" src="/qding-api/api-tools/console/lib/dialog.js"></script>
	<script type="text/javascript" src="/qding-api/api-tools/console/lib/dialog-plus.js"></script>
	<script type="application/javascript">

		var base = location.href.indexOf('qding-api') > 0 ? '/qding-api' : '';
		var protocolAlias,serviceAlias,methodAlias,version;
		var data = ${data};
		var desc = $('#doc');
		var methodCount = ${methodCount};
		var to = false;
		$("#methodCount").text(methodCount+"个");
		var vMethod=${vMethod};
		var vMethodCount="";
		var vMethods = ${vMethodNameList};
		var vMethodHtml="";
		var i=0;
		$.each(vMethod, function(key) {
			vMethodCount+="<p><a href='javascript:void(0);' style=\"color:red\" onclick=\"showMethods('"+i+"');\">"+key+" 版本接口数:"+vMethod[key]+"个</a></p>";
			var methodArray = vMethods[key]
			var methodHtml = "<div id='v_method_"+i+"'><ol class=\"rounded-list\">";
			$.each(methodArray, function(i, methodInfo){
				methodHtml += "<li><a href=\"javascript:void(0);\" onclick=\"getMethodInfo ('"+methodInfo.serviceAlias+"','"+methodInfo.methodAlias+"','"+methodInfo.version+"')\">"+methodInfo.methodAlias+" ("+methodInfo.explain+")</a></li>";
			});
			methodHtml+="</ol></div>";
			vMethodHtml+=methodHtml;
			i++;
		});
		$("#vMethodCount").html(vMethodCount);
		$("#vMethodHtml").append(vMethodHtml);
	</script>
	<script src="/qding-api/api-tools/console/lib/handle.js"></script>

</body>
</html>
	
	