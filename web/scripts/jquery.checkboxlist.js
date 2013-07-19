
function createControlFromJson(appendToId, jsonData, CellNoInRow) {

    var parentTable = document.createElement('table');
    parentTable.setAttribute('class', 'listTable');

    if (jsonData.length > 0) {
        var k = 0;
        var parentRow = null;
        $.each(jsonData, function(dataIndex, data) {

            if ((k + CellNoInRow) % CellNoInRow == 0)
            {
                parentRow = document.createElement('tr');
            }
            var cell = document.createElement('td');
            cell.setAttribute('Id', 'td_' + data.id);
            var checkbox = document.createElement('input');
            checkbox.id = "checkbox_" + data.id;
            checkbox.type = "checkbox";
            checkbox.value = data.name;
            if (data.Check) {
                checkbox.setAttribute('checked', 'checked');
            }
            var span = document.createElement('span');
            span.appendChild(document.createTextNode(data.name));
            cell.appendChild(checkbox);
            cell.appendChild(span);
            parentRow.appendChild(cell);
            if ((k + CellNoInRow) % CellNoInRow == 1 || k == jsonData.length - 1)
            {
                parentTable.appendChild(parentRow);
            }
            k++;
        });
    }
    document.getElementById(appendToId).appendChild(parentTable);
}

function checkCheckBoxList(appendToId, ids){
    $('#' + appendToId).find('table').find('tr').each(function() {
        $(this).find('td').each(function() {
            var id = $(this).find('input').attr('id');
            if (ids.indexOf(parseInt(id.split('_')[1])) > -1)
                $('#' + id).attr('checked', 'checked');
            else
                $('#' + id).removeAttr('checked');
        });
    });
}

function loadHiddenField(appendToId, destHiddenId){
    var data = '';
    $('#' + appendToId).find('table').find('tr').each(function() {
        $(this).find('td').each(function() {
            if ($(this).find('input').is(':checked'))
            {
                var id = $(this).find('input').attr('id').split('_')[1];
                if (data == '')
                    data = String(id);
                else
                    data = String(data) + ',' + String(id);
            }
        });
    });
    $('#' + destHiddenId).val(data);
}

(function($) {

    // jQuery plugin definition
    $.fn.addCheckboxList = function(appendToId, jsonData, CellNoInRow, submitId, destHiddenId) {
        //controlDetail = $.extend({}, $.fn.resourcecontrolnew.defaultOptions, controlDetail);

        // merge default and user parameters
        //controlDetail = $.extend({ controlType: '', controlNumber: 0, data: null });
        //CreateLivestockResourceTable(params.appendToId, params.type, params.startFiscalYear, params.endFiscalYear);
        //createDynamicControl(appendToId, type, controlDetail, jsonData);
        createControlFromJson(appendToId, jsonData, CellNoInRow, submitId, destHiddenId);
        $('#' + submitId).bind('click', function() {

        });
        var fnc = function() {
            loadHiddenField(appendToId, destHiddenId);
        };

        $('#' + submitId).click(fnc);
    };

    $.fn.addCheckboxList.defaultOptions = {
        "id": 0,
        "name": ''
    };


})(jQuery); 