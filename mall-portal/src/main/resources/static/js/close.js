layui.use(['layer'], function () {
    var layer = layui.layer;

    $('.close_data h5').click(function () {
        $('.close_data h5').css('borderColor', '#ccc');
        $('.close_data h5').removeAttr('checked');
        $(this).css('borderColor', '#ff6a00');
        $(this).attr('checked', true);
    })
    $('#closeId').on('click', '.close_add', function () {
        $('.close_add').css('borderColor', '#ccc');
        $('.close_add').removeAttr('checked');
        $(this).css('borderColor', '#ff6a00');
        $(this).attr('checked', true);
    })
    $('#closeId').on('click', '.close_add .del', function (e) {
        var id = $(this).parents('.close_add').attr('id');
        $.post(
            '/shipping/delete',
            {'id': id},
            function (result) {
                if (result.code === 0) {
                    mylayer.okMsg(result.msg);
                }
            },
            'json'
        )
        e.stopPropagation();  // 阻止事件冒泡
        $(this).parents('.close_add').remove();
    })
})

