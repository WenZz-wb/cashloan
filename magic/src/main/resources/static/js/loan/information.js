/**
 * 用户管理
 */
var pageCurr;
var form;
$(function() {
    layui.use('table', function(){
        var table = layui.table;
        form = layui.form;

        tableIns=table.render({
            elem: '#loanList',
            url:'/loan/getLoanList',
            method: 'post', //默认：get请求
            cellMinWidth: 80,
            page: true,
            request: {
                pageName: 'pageNum', //页码的参数名称，默认：pageNum
                limitName: 'pageSize' //每页数据量的参数名，默认：pageSize
            },
            response:{
                statusName: 'code',         //数据状态的字段名称，默认：code
                statusCode: 200,           //成功的状态码，默认：0
                countName: 'totals',       //数据总数的字段名称，默认：count
                dataName: 'list'           //数据列表的字段名称，默认：data
            },
            cols: [[
                {type:'checkbox'}
                ,{field:'loanID', title:'借款编号',align:'center'}
                ,{field:'loanName', title:'姓名',align:'center'}
                ,{field:'loanApplication', title:'申请日期',align:'center'}
                ,{field:'loanPhone', title: '手机号',align:'center'}
                ,{field:'loanQuota', title: '申请额度',align:'center'}
                ,{field:'loanRepayment', title:'还款方式',align:'center'}
                ,{field:'loanTerm', title:'借款期限(月)',align:'center'}
                ,{field:'loanType', title: '贷款类型',align:'center'}
                ,{field:'loanState', title: '状态',align:'center'}
                ,{field:'loanPreliminary', title: '初审时间',align:'center'}
                ,{field:'loanReview', title: '复审时间',align:'center'}
                ,{field:'preliminaryPersonnel', title: '初审人员',align:'center'}
                ,{field:'reviewPersonnel', title: '复审人员',align:'center'}
                ,{title:'操作',align:'center', toolbar:'#optBar'}
            ]],
            done: function(res, curr, count){
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                //console.log(res);
                //得到当前页码
                console.log(curr);
                $("[data-field='loanStatus']").children().each(function(){
                    if($(this).text()=='1'){
                        $(this).text("有效")
                    }else if($(this).text()=='0'){
                        $(this).text("失效")
                    }
                });
                //得到数据总量
                //console.log(count);
                pageCurr=curr;
            }
        });

        //监听工具条
        table.on('tool(loanTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                //删除
                delLoan(data,data.loanPhone,data.loanName);
            } else if(obj.event === 'edit'){
                //编辑
                openLoan(data,"编辑");
            }
        });

        //监听提交
        form.on('submit(loanSubmit)', function(data){
            // TODO 校验
            formSubmit(data);
            return false;
        });
    });

    //搜索框
    layui.use(['form','laydate'], function(){
        var form = layui.form ,layer = layui.layer
            ,laydate = layui.laydate;
        //日期
        laydate.render({
            elem: '#startTime'
        });
        laydate.render({
            elem: '#endTime'
        });
        laydate.render({
            elem: '#loanApplication'
        });
        laydate.render({
            elem: '#loanPreliminary'
        });
        laydate.render({
            elem: '#loanReview'
        });
        //TODO 数据校验
        //监听搜索框
        form.on('submit(searchSubmit)', function(data){
            //重新加载table
            load(data);
            return false;
        });
    });
});

// 刷新
$('#btn-refresh').on('click', function() {
    tableIns.reload();
});

//提交表单
function formSubmit(obj){
    $.ajax({
        type: "POST",
        data: $("#loanForm").serialize(),
        url: "/loan/addLoan",
        success: function (data) {
            if (data.code == 1) {
                layer.alert(data.message,function(){
                    layer.closeAll();
                    load(obj);
                });
            } else {
                layer.alert(data.message);
            }
        },
        error: function () {
            layer.alert("操作请求错误，请您稍后再试",function(){
                layer.closeAll();
                //加载load方法
                load(obj);//自定义
            });
        }
    });
}

//添加贷款信息
function addLoan(){
    openLoan(null,"添加贷款信息");
}
function openLoan(data,title){
    var roleId = null;
    if(data==null || data==""){
        $("#id").val("");
    }else{
        $("#loanname").val(data.loanName);
        $("#mobile").val(data.loanPhone);
        roleId = data.loanPhone;
    }
    var pageNum = $(".layui-laypage-skip").find("input").val();
    $("#pageNum").val(pageNum);
    layer.open({
        type:1,
        title: title,
        fixed:false,
        resize :false,
        shadeClose: true,
        area: ['550px'],
        content:$('#setLoan'),
        end:function(){
            cleanLoan();
        }
    });
}

function delLoan(obj,phone,name) {
    var currentLoan=$("#currentLoan").html();
    if(null!=phone){
            layer.confirm('您确定要删除'+name+'用户吗？', {
                btn: ['确认','返回'] //按钮
            }, function(){
                $.post("/loan/deleteLoanStatus",{"phone":phone,"name":name},function(data){
                    if (data.code == 1) {
                        layer.alert(data.message,function(){
                            layer.closeAll();
                            load(obj);
                        });
                    } else {
                        layer.alert(data.message);
                    }
                });
            }, function(){
                layer.closeAll();
            });

    }
}
/*
//恢复
function recoverLoan(obj,id) {
    if(null!=id){
        layer.confirm('您确定要恢复吗？', {
            btn: ['确认','返回'] //按钮
        }, function(){
            $.post("/loan/updateLoanStatus",{"id":id,"status":1},function(data){
                if (data.code == 1) {
                    layer.alert(data.message,function(){
                        layer.closeAll();
                        load(obj);
                    });
                } else {
                    layer.alert(data.message);
                }
            });
        }, function(){
            layer.closeAll();
        });
    }
}
*/



function load(obj){
    //重新加载table
    tableIns.reload({
        where: obj.field
        , page: {
            curr: pageCurr //从当前页码开始
        }
    });
}

function cleanLoan(){
    $("#loanName").val("");
    $("#loanApplication").val("");
    $("#loanPhone").val("");
    $('#loanQuota').val("");
    $("#loanRepayment").val("");
    $("#loanTerm").val("");
    $("#loanType").val("");
    $('#loanState').val("");
    $("#loanPreliminary").val("");
    $('#loanReview').val("");
    $("#preliminaryPersonnel").val("");
    $('#reviewPersonnel').val("");
}
