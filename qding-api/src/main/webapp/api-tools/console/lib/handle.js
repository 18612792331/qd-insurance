/**
 * Created by qd on 2016/1/14.
 */
$('#search').keyup(function () {
    if (to) {
        clearTimeout(to);
    }
    to = setTimeout(function () {
        var v = $('#search').val();
        $('#tree').jstree(true).search(v, true, true);
    }, 250);
});

$('#tree').jstree({
    'core': {
        'data': data,
        'themes': {"name": "default-dark"}
    }, "plugins": ["search"]
}).on('activate_node.jstree', function (e, data) {
    //	var doc = data.node.data;
    //第三级
    var parents = data.node.parent.split(':');
    if (parents.length > 1) {
        version = parents[1];
        serviceAlias = parents[0];
        var  idParames=data.node.id.split(':');
        methodAlias = idParames[2];
        //methodAlias = data.node.text;
        getMethodInfo (serviceAlias,methodAlias,version);
    }
});

function  getMethodInfo (serviceAlias,methodAlias,version){

    $.get(base + '/tools/method', {
        'serviceAlias': serviceAlias,
        'methodAlias': methodAlias,
        'version': version
    }, function (data) {
        var doc = data.data;
        desc.html('<table id="table"></table><table> <tr><td colspan="2" id="request" style="vertical-align:initial"></td></tr> <tr><td colspan="2" id="response" style="vertical-align:initial"></td></tr></table>');
        var table = $('#table', desc);

        for (var i = 0; i < doc.length; i++) {

            if (doc[i].key.indexOf('TREE:REQUEST') != -1) {

                $('#request').jstree({
                    'core': {
                        'data': eval("(" + doc[i].value + ")"),
                        'themes': {"name": "default-dark"}
                    }
                }).bind('loaded.jstree', function (e, data) {
                    data.instance.open_all();
                    data.instance.refresh();
                });

            }
            else if (doc[i].key.indexOf('TREE:RESPONSE') != -1) {
                $('#response').jstree({
                    'core': {
                        'data': eval("(" + doc[i].value + ")"),
                        'themes': {"name": "default-dark"}
                    }
                }).bind('loaded.jstree', function (e, d) {
                    d.instance.open_all();
                });
            }
            else {
                if(i==8 || i==9){
                    table.append('<tr><td>' + doc[i].key + '</td><td style=\'color:red\'>' + doc[i].value + '</td></tr>');
                } else {
                    table.append('<tr><td>' + doc[i].key + '</td><td>' + doc[i].value + '</td></tr>');
                }
             
            }
        }
        $("#queryString").val(data.body);
        $("#testReuqest").val("");
        $("#testQuery").val("");
        $("#testReuqest").val(data.testBody);
        $("#mockData").val(data.mockData);
        $("#testQuery").val(syntaxHighlight(data.testBody));
        

    });
}

function  showMethods(key){
    var vMethodsDialog = dialog({
        title: key+"版本新增方法",
        content: $('#v_method_'+key).html(),
        cancel: true,
        height:'100%',
        width:'100%',
        padding: 10,
        zIndex: 100,
        okValue: "提交请求",
        cancelDisplay: false
    });
    vMethodsDialog.show();
}


$('#debug').click(function () {

    var responseDialog = dialog({
        title:"API调用接口返回",
        content:"正在请求数据，请稍等........",
        cancel:true,
        onshow: function () {
            protocolAlias = $("#protocolAlias").val();
            if (!protocolAlias) {
                return;
            }
            if (!serviceAlias) {
                return;
            }
            if (!methodAlias) {
                return;
            }
            var url = base + '/api/{protocolAlias}/{serviceAlias}/{methodAlias}'
                    .replace('{protocolAlias}', protocolAlias)
                    .replace('{serviceAlias}', serviceAlias)
                    .replace('{methodAlias}', methodAlias);
            var startTime=new Date().getTime();
            $.ajax({
                type: $('#method').val(),
                url: url,
                data: $("#queryString").val(),
                dataType: 'html',
                success: function (data) {
                    var endTime=new Date().getTime();
                    responseDialog.title("API调用接口返回 总耗时："+(endTime-startTime));
                    var responseStr = "<pre>"+syntaxHighlight(data)+"</pre>";
                    responseDialog.content(responseStr);
                   }
                });
        },
        width: '100%',
        height:'100%',
        padding:20,
        zIndex: 100,
        cancelValue:"关闭",
        okValue:"更新用例",
        ok:function(){
            var queryStr = $("#queryString").val();
            if (queryStr){
                $.ajax({
                    type:'POST',
                    dataType:'json',
                    url:base + '/tools/saveParameter',
                    data:{'serviceAlias': serviceAlias, 'methodAlias': methodAlias, 'version': version,'parameterStr':queryStr.replace("body=","")},
                    success:function(data){
                        var remind = dialog({
                            title: '提示',
                            content: data.msg
                        });
                        remind.show();
                        setTimeout(function () {
                            remind.close().remove();
                        }, 2000);
                        var queryStr = $("#queryString").val();
                        var parameterStr = queryStr.replace("body=","");
                        $("#testReuqest").val(parameterStr);
                        $("#testQuery").val(syntaxHighlight(parameterStr));
                    }
                })
            }
            return false;
        }

    });
    responseDialog.showModal();
});


$("#example").click(function(){

    var testQuery = $("#testQuery").val();
    if (!testQuery){
        var remind = dialog({
            title: '提示',
            content: "没有新的测试用例！"
        });
        remind.show();
        setTimeout(function () {
            remind.close().remove();
        }, 2000);
        return false;
    }

    var exampleDialog = dialog({
        title:"API测试用例",
        content: "<pre>"+$("#testQuery").val()+"</pre>",
        cancel:true,
        width: '100%',
        height:'100%',
        padding:20,
        zIndex: 100,
        okValue:"提交请求",
        cancelDisplay:false,
        ok:function(){
            exampleDialog.content("正在请求数据，请稍等........");
            protocolAlias = $("#protocolAlias").val();

            if (!protocolAlias) {
                return;
            }
            if (!serviceAlias) {
                return;
            }
            if (!methodAlias) {
                return;
            }
            var url = base + '/api/{protocolAlias}/{serviceAlias}/{methodAlias}'
                    .replace('{protocolAlias}', protocolAlias)
                    .replace('{serviceAlias}', serviceAlias)
                    .replace('{methodAlias}', methodAlias);
            var startTime=new Date().getTime();
            $.ajax({
                type: $('#method').val(),
                url: url,
                data: "body="+$("#testReuqest").val(),
                dataType: 'html',
                success: function (data) {
                    var endTime=new Date().getTime();
                    exampleDialog.title("API测试用例 总耗时："+(endTime-startTime));
                    var responseStr = syntaxHighlight(data);
                    exampleDialog.content("<pre>"+responseStr+"</pre>");
                }
            });
            return false;
        },
        button: [
            {
                value: '删除此用例',
                callback: function () {
                    $.ajax({
                        type:'POST',
                        dataType:'json',
                        url:base + '/tools/delParameter',
                        data:{'serviceAlias': serviceAlias, 'methodAlias': methodAlias, 'version': version},
                        success:function(data){
                            $("#testReuqest").val("");
                            $("#testQuery").val("");
                            var remind = dialog({
                                title: '提示',
                                content: data.msg
                            });
                            exampleDialog.close();
                            remind.show();
                            setTimeout(function () {
                                remind.close().remove();
                            }, 2000);
                        }
                    });
                    return false;
                }
            }
        ]
    });
    exampleDialog.showModal();
});



function syntaxHighlight(json) {
    if (typeof json != 'string') {
        json = JSON.stringify(json, undefined, 2);
    }
    json = json.replace(/&/g, '&').replace(/</g, '<').replace(/>/g, '>');
    return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
        var cls = 'number';
        if (/^"/.test(match)) {
            if (/:$/.test(match)) {
                cls = 'key';
            } else {
                cls = 'string';
            }
        } else if (/true|false/.test(match)) {
            cls = 'boolean';
        } else if (/null/.test(match)) {
            cls = 'null';
        }
        return '<span class="' + cls + '">' + match + '</span>';
    });
}