findAllSnacks();

function findAllSnacks(){
    $("#spinner").toggle();
    $.get('http://'+window.location.hostname+'/snack-api/snacks', function (snacks) {

        $.each(snacks, function(i, snack) {

            $('#snacks').append('<li class="products-item">' +
                        '<img class="responsive-image" src="img/snacks.jpg" alt="">' +
                        '<ul>' +
                        '<li><span>Name</span> '+snack.name+'</li>' +
                        '<li><span>Price</span> '+snack.total +'</li>' +
                        '<li><span>Ingredients</span>'+ mountIngredients(snack.ingredientItems) +'</li>' +
                        '</ul>' +
                        '<a href="#">Editar</a>'+
                        '</li>');
        });

    })
    .fail(function(){
        $("#erro").toggle();
        setTimeout(function(){
            $("#erro").toggle();
        },1500);
    })
    .always(function(){
       $("#spinner").toggle();
    });

}

function mountIngredients(ingredientItems) {
    var arr = [];
    $.each(ingredientItems, function (i) {
        arr[i] = (ingredientItems[i].ingredient.name)
    })
    return arr.join(",");
}


