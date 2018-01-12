function setNavtabform(formId,data) {
    var $form = $.CurrentNavtab.find(formId);
    // data =  JSON.stringify(data);
    for(var key in data){
        var val = data[key];
        if(!_checkSelectField($form,key,val)){
            if(!_checkField($form,key,val)){
                var elem=$form.find("input[name='"+key+"']");
                if(elem){
                    elem.val(val);
                    continue;
                }
            }
        }
        _checkDateField($form, key, val);
    }

}

function setNativeform(formId,data) {
    var $form = $(formId);
    // data =  JSON.stringify(data);
    for(var key in data){
        var val = data[key];
        if(!_checkSelectField($form,key,val)){
            if(!_checkField($form,key,val)){
                var elem=$form.find("input[name='"+key+"']");
                if(elem){
                    elem.val(val);
                    continue;
                }
            }
        }
        _checkDateField($form, key, val);
    }
}

/**
 * check the datepicker
 */
function _checkDateField($form, key, val){
    var elem=$form.find("input[name='"+key+"'][data-toggle='datepicker']");
    if(elem && elem.length>0){
        var pattern =  elem.attr("data-pattern") ;
        pattern = pattern ? "yyyy-MM-dd" : pattern;
        elem.datepicker({pattern:pattern, minDate:val});
    }
}

/**
 * check the checkbox and radio fields
 */
function _checkField($form, key, val){
    var elem=$form.find("input[type='checkbox'][name='"+key+"']");
    if(elem && elem.length>0){
        elem.iCheck("check");
        elem.iCheck('update');
        return true;
    }
    elem=$form.find("input[type=''][name='"+key+"'][value='"+val+"']");
    if(elem && elem.length>0){
        elem.iCheck("check");
        elem.iCheck('update');
        return true;
    }
    return false;
}

function _checkSelectField($form, key, val){
    var elem=$form.find("select[name='"+key+"']");
    if(elem && elem.length>0){
        elem.selectpicker('val', val);
        return true;
    }
    return false;
}


function formDisable($form,disabled){

    var $form = $.CurrentNavtab.find(formId);

    $form.find(":input").attr("disabled", disabled ? 'disabled' : '');

    $form.find(":radio,:checkbox").each(function (){
        $(this).iCheck(disabled ? "disabled" : "enable");
    });

    $form.find("select").each(function () {
        $(this).prop('disabled', disabled);
        $(this).selectpicker('refresh');
    });

    $form.find("input[data-toggle='datepicker']").each(function () {
        $(this).attr("disabled", disabled ? 'disabled' : '')
    });
}


function clearForm(formId){
    var $form = $.CurrentNavtab.find(formId);
    $form.find(":radio,:checkbox").each(function (){
        $(this).iCheck("uncheck");
    });
    $form.find("select").each(function () {
        $(this).selectpicker('deselectAll');
        $(this).selectpicker('refresh');
        $(this).selectpicker('val', '');
    });
    $form.find("input[data-toggle='datepicker']").each(function () {
            $(this).datepicker({minDate:''});
    });
    $form.find(":input").val("");
}