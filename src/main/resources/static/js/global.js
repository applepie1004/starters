const errorMsg = (target, message) => {
    target.siblings("p").text('');
    target.siblings("p").text(message);
    target.parent().addClass('error');
}