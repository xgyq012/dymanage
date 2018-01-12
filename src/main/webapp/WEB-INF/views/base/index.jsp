<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title></title>
    <!-- bootstrap - css -->
    <link href="${ctx}/resources/js/B-JUI/themes/css/bootstrap.css" rel="stylesheet">
    <!-- core - css -->
    <link href="${ctx}/resources/js/B-JUI/themes/css/style.css" rel="stylesheet">
    <link href="${ctx}/resources/js/B-JUI/themes/blue/core.css" id="bjui-link-theme" rel="stylesheet">
    <link href="${ctx}/resources/js/B-JUI/themes/css/fontsize.css" id="bjui-link-theme" rel="stylesheet">
    <!-- plug - css -->
    <link href="${ctx}/resources/js/B-JUI/plugins/kindeditor_4.1.11/themes/default/default.css" rel="stylesheet">
    <link href="${ctx}/resources/js/B-JUI/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
    <link href="${ctx}/resources/js/B-JUI/plugins/nice-validator-1.0.7/jquery.validator.css" rel="stylesheet">
    <link href="${ctx}/resources/js/B-JUI/plugins/bootstrapSelect/bootstrap-select.css" rel="stylesheet">
    <link href="${ctx}/resources/js/B-JUI/plugins/webuploader/webuploader.css" rel="stylesheet">
    <link href="${ctx}/resources/js/B-JUI/themes/css/FA/css/font-awesome.min.css" rel="stylesheet">

    <link href="${ctx}/resources/css/index.css" rel="stylesheet">

    <!-- Favicons -->
   <%-- <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-precomposed.png">
    <link rel="shortcut icon" href="assets/ico/favicon.png">--%>
    <!--[if lte IE 7]>
    <link href="${ctx}/resources/js/B-JUI/themes/css/ie7.css" rel="stylesheet">
    <![endif]-->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lte IE 9]>
    <script src="${ctx}/resources/js/B-JUI/other/html5shiv.min.js"></script>
    <script src="${ctx}/resources/js/B-JUI/other/respond.min.js"></script>
    <![endif]-->
    <!-- jquery -->
    <script src="${ctx}/resources/js/B-JUI/js/jquery-1.11.3.min.js"></script>
    <script src="${ctx}/resources/js/B-JUI/js/jquery.cookie.js"></script>
    <!--[if lte IE 9]>
    <script src="${ctx}/resources/js/B-JUI/other/jquery.iframe-transport.js"></script>
    <![endif]-->
    <!-- B-JUI -->
    <script src="${ctx}/resources/js/B-JUI/js/bjui-all.js"></script>
    <!-- plugins -->
    <!-- swfupload for kindeditor -->
    <script src="${ctx}/resources/js/B-JUI/plugins/swfupload/swfupload.js"></script>
    <!-- Webuploader -->
    <script src="${ctx}/resources/js/B-JUI/plugins/webuploader/webuploader.js"></script>
    <!-- kindeditor -->
    <script src="${ctx}/resources/js/B-JUI/plugins/kindeditor_4.1.11/kindeditor-all-min.js"></script>
    <script src="${ctx}/resources/js/B-JUI/plugins/kindeditor_4.1.11/lang/zh-CN.js"></script>
    <!-- colorpicker -->
    <script src="${ctx}/resources/js/B-JUI/plugins/colorpicker/js/bootstrap-colorpicker.min.js"></script>
    <!-- ztree -->
    <script src="${ctx}/resources/js/B-JUI/plugins/ztree/jquery.ztree.all-3.5.js"></script>
    <!-- nice validate -->
    <script src="${ctx}/resources/js/B-JUI/plugins/nice-validator-1.0.7/jquery.validator.js"></script>
    <script src="${ctx}/resources/js/B-JUI/plugins/nice-validator-1.0.7/jquery.validator.themes.js"></script>
    <!-- bootstrap plugins -->
    <script src="${ctx}/resources/js/B-JUI/plugins/bootstrap.min.js"></script>
    <script src="${ctx}/resources/js/B-JUI/plugins/bootstrapSelect/bootstrap-select.min.js"></script>
    <script src="${ctx}/resources/js/B-JUI/plugins/bootstrapSelect/defaults-zh_CN.min.js"></script>
    <!-- icheck -->
    <script src="${ctx}/resources/js/B-JUI/plugins/icheck/icheck.min.js"></script>
    <!-- HighCharts -->
    <script src="${ctx}/resources/js/B-JUI/plugins/highcharts/highcharts.js"></script>
    <script src="${ctx}/resources/js/B-JUI/plugins/highcharts/highcharts-3d.js"></script>
    <script src="${ctx}/resources/js/B-JUI/plugins/highcharts/themes/gray.js"></script>
    <!-- other plugins -->
    <script src="${ctx}/resources/js/B-JUI/plugins/other/jquery.autosize.js"></script>
    <link href="${ctx}/resources/js/B-JUI/plugins/uploadify/css/uploadify.css" rel="stylesheet">
    <script src="${ctx}/resources/js/B-JUI/plugins/uploadify/scripts/jquery.uploadify.min.js"></script>
    <script src="${ctx}/resources/js/B-JUI/plugins/download/jquery.fileDownload.js"></script>

    <script src="${ctx}/resources/js/B-JUI/form.js"></script>

    <script type="text/javascript">
        $(function() {
            BJUI.init({
                JSPATH       : 'B-JUI/',         //[可选]框架路径
                PLUGINPATH   : '${ctx}/resources/js/B-JUI/plugins/', //[可选]插件路径
                //  loginInfo    : {url:'login_timeout.html', title:'登录', width:440, height:240}, // 会话超时后弹出登录对话框
                statusCode   : {ok:0, error:-1, timeout:301}, //[可选]
                ajaxTimeout  : 300000, //[可选]全局Ajax请求超时时间(毫秒)
                alertTimeout : 3000,  //[可选]信息提示[info/correct]自动关闭延时(毫秒)
                pageInfo     : {total:'total', pageCurrent:'page', pageSize:'rows', orderField:'orderField', orderDirection:'orderDirection'}, //[可选]分页参数
                alertMsg     : {displayPosition:'topcenter', displayMode:'slide', alertTimeout:3000},
                keys         : {statusCode:'status', message:'msg'}, //[可选]
                ui           : {
                    sidenavWidth     : 220,
                    showSlidebar     : true, //[可选]左侧导航栏锁定/隐藏
                    overwriteHomeTab : false //[可选]当打开一个未定义id的navtab时，是否可以覆盖主navtab(我的主页)
                },
                debug        : true,    // [可选]调试模式 [true|false，默认false]
                theme        : 'green' // 若有Cookie['bjui_theme'],优先选择Cookie['bjui_theme']。皮肤[五种皮肤:default, orange, purple, blue, red, green]
            })

        });


        // 满屏开关
        var bjui_index_container = 'container_fluid'

        function bjui_index_exchange() {
            bjui_index_container = bjui_index_container == 'container_fluid' ? 'container' : 'container_fluid'

            $('#bjui-top').find('> div').attr('class', bjui_index_container)
            $('#bjui-navbar').find('> div').attr('class', bjui_index_container)
            $('#bjui-body-box').find('> div').attr('class', bjui_index_container)
        }

    </script>

</head>
<body>

<div id="bjui-top" class="bjui-header">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapsenavbar" data-target="#bjui-top-collapse" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <nav class="collapse navbar-collapse" id="bjui-top-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="javascript:;" onclick="bjui_index_exchange()" title="横向收缩/充满屏幕"><i class="fa fa-exchange"></i></a></li>
            </ul>
        </nav>
    </div>
</div>
<header class="navbar bjui-header" id="bjui-navbar">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" id="bjui-navbar-collapsebtn" data-toggle="collapsenavbar" data-target="#bjui-navbar-collapse" aria-expanded="false">
                <i class="fa fa-angle-double-right"></i>
            </button>
        </div>
        <nav class="collapse navbar-collapse" id="bjui-navbar-collapse">
            <ul class="nav navbar-nav navbar-right" id="bjui-hnav-navbar">

            </ul>
        </nav>
    </div>
</header>
<div id="bjui-body-box">
    <div class="container" id="bjui-body">
        <div id="bjui-sidenav-col">
            <div id="bjui-sidenav">
                <div id="bjui-sidenav-arrow" data-toggle="tooltip" data-placement="left" data-title="隐藏左侧菜单">
                    <i class="fa fa-long-arrow-left"></i>
                </div>
                <div id="bjui-sidenav-box">
                    <ul class="nav">
                        <li class=""><a href="javascript:;" class="right-arrow"><i class="fa fa-caret-right"></i>&nbsp;表单相关<b><i
                                class="fa fa-angle-right"></i></b></a>
                            <ul class="nav" style="display: block;">
                                <li class="navtab-base-button"><a href="${ctx}/saleGoodsType/list" data-toggle="navtab"
                                                                  data-options="{id:'sale-goodsType', title:'商品类型'}"><i
                                        class="fa fa-caret-right"></i>&nbsp;商品类型</a></li>
                                <li class="navtab-base-input"><a href="${ctx}/resource/list" data-toggle="navtab"
                                                                 data-options="{id:'base-resource', title:'资源管理'}"><i
                                        class="fa fa-caret-right"></i>&nbsp;资源管理</a></li>
                                <li class="navtab-base-select"><a href="${ctx}/goods/list" data-toggle="navtab"
                                                                  data-options="{id:'sale-goods', title:'商品信息'}"><i
                                        class="fa fa-caret-right"></i>&nbsp;商品信息</a></li>
                                <li class="navtab-base-checkbox"><a href="html/form/checkbox.html" data-toggle="navtab"
                                                                    data-options="{id:'base-checkbox', title:'单选、复选框'}"><i
                                        class="fa fa-caret-right"></i>&nbsp;单选、复选框</a></li>
                                <li class="navtab-base-datepicker"><a href="html/form/datepicker.html"
                                                                      data-toggle="navtab"
                                                                      data-options="{id:'base-datepicker', title:'日期选择器'}"><i
                                        class="fa fa-caret-right"></i>&nbsp;日期选择器</a></li>
                                <li class="navtab-base-tags"><a href="html/form/tags.html" data-toggle="navtab"
                                                                data-options="{id:'base-tags', title:'Tags、自动完成'}"><i
                                        class="fa fa-caret-right"></i>&nbsp;Tags、自动完成</a></li>
                                <li class="navtab-base-suggest"><a href="html/form/suggest.html" data-toggle="navtab"
                                                                   data-options="{id:'base-suggest', title:'Suggest'}"><i
                                        class="fa fa-caret-right"></i>&nbsp;Suggest</a></li>
                                <li class="navtab-base-findgrid"><a href="html/form/findgrid.html" data-toggle="navtab"
                                                                    data-options="{id:'base-findgrid', title:'FindGrid'}"><i
                                        class="fa fa-caret-right"></i>&nbsp;FindGrid</a></li>
                                <li class="navtab-base-spinner"><a href="html/form/spinner.html" data-toggle="navtab"
                                                                   data-options="{id:'base-spinner', title:'Spinner'}"><i
                                        class="fa fa-caret-right"></i>&nbsp;Spinner</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div id="bjui-navtab" class="tabsPage">
            <div id="bjui-sidenav-btn" data-toggle="tooltip" data-title="显示左侧菜单" data-placement="right">
                <i class="fa fa-bars"></i>
            </div>
            <div class="tabsPageHeader">
                <div class="tabsPageHeaderContent">
                    <ul class="navtab-tab nav nav-tabs">
                        <li><a href="javascript:;"><span><i class="fa fa-home"></i> #maintab#</span></a></li>
                    </ul>
                </div>
                <div class="tabsLeft"><i class="fa fa-angle-double-left"></i></div>
                <div class="tabsRight"><i class="fa fa-angle-double-right"></i></div>
                <div class="tabsMore"><i class="fa fa-angle-double-down"></i></div>
            </div>
            <ul class="tabsMoreList">
                <li><a href="javascript:;">#maintab#</a></li>
            </ul>
            <div class="navtab-panel tabsPageContent">
                <div class="navtabPage unitBox">
                    <div class="bjui-pageContent">
                        <div class="highlight">
                                <pre class="prettyprint">
                                    ------------------------
                                    BJUI 更新至 V1.31
                                    ------------------------
                                    [修复] datagrid参数templateWidth、dialogFilterW为0时默认为启用；修复排序bug；新增字段参数itemattr，为items参数指定key/value；新增方法filter，用于数据筛选。
                                    [修复] 分页组件。
                                    [更新] ajaxform、ajaxsearch新增参数validate，是否验证标记。
                                    [更新] 验证插件nice validate更新至1.0.7。
                                    [更新] 图标字体Font Awesome更新至4.7.0。
                                    [调整] CSS细微调整。
                                    ------------------------

                                    　　　　　　2016-11-01 by.萧克南
                                </pre>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

var GlobalConfig= {

    ctx : '${ctx}'

}

</script>
</body>

</html>