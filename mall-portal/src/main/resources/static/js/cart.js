layui.use(['layer'], function () {
    var layer = layui.layer;

    //初始化总价, 总选择数, 总条数;
    doPrice();
    //全选/选择框的鼠标移上变个颜色
    $('.fa-check').mouseover(function () {
        if ($(this).attr('class') != 'fa fa-check checked') {
            $(this).css('color', '#ff6a00');
        }
    })
    $('.fa-check').mouseleave(function () {
        $(this).css('color', '#fff');
    })

    //普通勾选
    // $('.fa-check').click(function () {
    //     var cla = $(this).attr('class');
    //     if (cla != 'fa fa-check checked') {
    //         $(this).attr('class', 'fa fa-check checked');
    //     } else {
    //         $(this).attr('class', 'fa fa-check');
    //     }
    //     doCheckAll();
    //     doPrice();
    // })
    $('#cartId').on('click', '.fa-check', function () {
        var cla = $(this).attr('class');
        var id = $(this).parents('.list-item').attr('id');
        var checked;
        if (cla != 'fa fa-check checked') {
            $(this).attr('class', 'fa fa-check checked');
            checked = 1;
        } else {
            $(this).attr('class', 'fa fa-check');
            checked = 0;
        }
        $.post(
            '/cart/update',
            {
                'id': id,
                'checked': checked
            },
            function (result) {
                console.log(result);
                if (result.code == 0) {
                    mylayer.okMsg(result.msg);
                }
            },
            'json'
        );
        doCheckAll();
        doPrice();
    })

    //全选框勾选
    $('#check-all').click(function () {
        var cla = $(this).attr('class');
        var checked;
        if (cla != 'fa fa-check checked') {
            $('.fa-check').attr('class', 'fa fa-check checked');
            checked = 1;
        } else {
            $('.fa-check').attr('class', 'fa fa-check');
            checked = 0;
        }
        $.post(
            '/cart/updateCheckedAll',
            {'checked': checked},
            function (result) {
                console.log(result);
                if (console.code == 0) {
                    mylayer.okMsg(result.msg);
                }
            },
            'json'
        );
        doPrice();
    })

    //检查是否全选
    function doCheckAll() {
        var allitem = $('.list-item i[class*="fa-check"]').length;
        var checkeditem = $('.list-item i[class$="checked"]').length;
        if (allitem != checkeditem) {
            $('#check-all').attr('class', 'fa fa-check');
        } else {
            $('#check-all').attr('class', 'fa fa-check checked');
        }
    }

    //删除按钮
    $('#cartId').on('click', '.fa-times', function () {
        var id = $(this).parents('.list-item').attr('id');
        layer.confirm('您确认要删除么?', function () {
            $.post(
                '/cart/deleteById',
                {
                    'id': id,
                },
                function (result) {
                    console.log(result);
                    if (result.code == 0) {
                        mylayer.okUrl(result.msg, '/page/cart/list');
                    }
                },
                'json'
            );
        });
    })

    //修改商品数量
    $('#cartId').on('change', '.num-value', function () {
        var id = $(this).parents('.list-item').attr('id');
        var quantity = this.value;
        $.post(
            '/cart/update',
            {
                'id': id,
                'quantity': quantity
            },
            function (result) {
                console.log(result);
                if (result.code == 0) {
                    mylayer.okMsg(result.msg);
                }
            },
            'json'
        );
    })

    //加减按钮
    $('#cartId').on('click', 'button.minus', function () {
        var nowvalue = $(this).siblings('input').val();
        nowvalue = parseInt(nowvalue);
        var currentvalue = 0;
        nowvalue <= 1 ? currentvalue = 1 : currentvalue = nowvalue - 1;
        $(this).siblings('input').val(currentvalue);

        //计算当前的小计
        var danjia = parseFloat($(this).parents('.good-num').siblings('.good-price').html());
        var xiaoji = danjia * currentvalue;
        $(this).parents('.good-num').siblings('.good-total-price').html(xiaoji + '元');

        var id = $(this).parents('.list-item').attr('id');
        $.post(
            '/cart/minus',
            {
                'id': id,
                'quantity': currentvalue
            },
            function (result) {
                console.log(result);
                if (result.code == 0) {
                    mylayer.okMsg(result.msg);
                }
            },
            'json'
        );

        //更新总价
        doPrice();
    })
    $('#cartId').on('click', 'button.plus', function () {
        var nowvalue = $(this).siblings('input').val();
        nowvalue = parseInt(nowvalue);
        var currentvalue = nowvalue + 1;
        $(this).siblings('input').val(currentvalue);

        //计算当前的小计
        var danjia = parseFloat($(this).parents('.good-num').siblings('.good-price').html());
        var xiaoji = danjia * currentvalue;
        $(this).parents('.good-num').siblings('.good-total-price').html(xiaoji + '元');

        var id = $(this).parents('.list-item').attr('id');
        $.post(
            '/cart/plus',
            {
                'id': id,
                'quantity': currentvalue
            },
            function (result) {
                console.log(result);
                if (result.code == 0) {
                    mylayer.okMsg(result.msg);
                }
            },
            'json'
        );

        //更新总价
        doPrice();
    })
    //加减按钮

    // $('button.minus').click(function () {
    //     var nowvalue = $(this).siblings('input').val();
    //     nowvalue = parseInt(nowvalue);
    //     var currentvalue = 0;
    //     nowvalue <= 1 ? currentvalue = 1 : currentvalue = nowvalue - 1;
    //     $(this).siblings('input').val(currentvalue);
    //
    //     //计算当前的小计
    //     var danjia = parseFloat($(this).parents('.good-num').siblings('.good-price').html());
    //     var xiaoji = danjia * currentvalue;
    //     $(this).parents('.good-num').siblings('.good-total-price').html(xiaoji + '元');
    //
    //     //更新总价
    //     doPrice();
    // })
    //
    // $('button.plus').click(function () {
    //     var nowvalue = $(this).siblings('input').val();
    //     nowvalue = parseInt(nowvalue);
    //     var currentvalue = nowvalue + 1;
    //     $(this).siblings('input').val(currentvalue);
    //
    //     //计算当前的小计
    //     var danjia = parseFloat($(this).parents('.good-num').siblings('.good-price').html());
    //     var xiaoji = danjia * currentvalue;
    //     $(this).parents('.good-num').siblings('.good-total-price').html(xiaoji + '元');
    //
    //     //更新总价
    //     doPrice();
    // })

    function doPrice() {
        //统计所有勾选了的值;
        var items = $('.list-item i[class*="fa-check"]');
        var checkeditems = $('.list-item i[class$="checked"]').parents('.select').siblings('.good-total-price')
        var totalprice = 0;
        for (var i = 0; i < checkeditems.length; i++) {
            totalprice += parseFloat(checkeditems[i].innerHTML);
        }
        //改总价
        $('.sum-price').html(totalprice);
        //改选中数
        $('.select-count').html(checkeditems.length);
        //改总条数
        $('.all-count').html(items.length);

    }
})