findAllIngredients();


function findAllIngredients(){
    $("#spinner").toggle();

    $.get('http://'+window.location.hostname+'/snack-api/ingredients', function (ingredients) {
        $.each(ingredients, function(i, ingredient) {

            $('#ingredients').append('<li class="products-item">' +
                '<img class="responsive-image" src="img/snacks.jpg" alt="">' +
                '<ul>' +
                '<li><span>Name</span> '+ingredient.name+'</li>' +
                '<li><span>Price</span> '+ingredient.value +'</li>' +
                '</ul>' +
                '<a href="#">Editar</a>'+
                '</li>');
        });

    })
    .fail(function(){
        $("#spinner").toggle();
        $("#erro").toggle();
        setTimeout(function(){
            $("#erro").toggle();
        },1500);
    })
    .always(function(){
        $("#spinner").toggle();
    });
}



