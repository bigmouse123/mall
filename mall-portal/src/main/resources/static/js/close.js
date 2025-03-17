$(function () {
    $('.close_data h5').click(function () {
        $('.close_data h5').css('borderColor', '#ccc');
        $(this).css('borderColor', '#ff6a00');
    })
    $('#closeId').on('click', '.close_add', function () {
        $('.close_add').css('borderColor', '#ccc');
        $('.close_add').removeAttr('checked');
        $(this).css('borderColor', '#ff6a00');
        $(this).attr('checked', true);
    })
    $('.close_add .del').click(function () {
        $(this).parents('.close_add').remove();
    })
})

