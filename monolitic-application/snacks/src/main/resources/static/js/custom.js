loadIngredients();


$("#add-ingredient").click(function (event) {
    event.preventDefault();
    insertIngredient()
});

$("#calculate-offer").click(function (event) {
    event.preventDefault();
    var lines = $("tbody>tr");
    if (lines.length == 0) {
        $("#msg").text("You should select at least one ingredient :)");

    } else {
        $("#msg").text("");
        calculatePrice();
    }
});

function snackModel() {
    var lines = $("tbody>tr");

    var ingredientItems = []

    lines.each(function () {

        var id = $(this).find("td:nth-child(1)").text();
        var name = $(this).find("td:nth-child(2)").text();
        var quantity = $(this).find("td:nth-child(3)").text();

        var ingredient = {};

        ingredient.id = id;
        ingredient.name = name;
        ingredientItems.push({quantity: quantity, ingredient: ingredient});
    })
    var body = {ingredientItems: ingredientItems}
    return body;
}

function calculatePrice() {
    $("#spinner").toggle();

    $.ajax({
        url: "http://"+window.location.hostname+"/snack-api/custom",
        type: "POST",
        data: JSON.stringify(snackModel()),
        contentType: "application/json",
        dataType: "json",
        success: function (response) {
            $("#total").text('You will only pay: $' + response.total);

            $("#box").dialog({
                title: 'Your promotion',
                width: 'auto',
                maxWidth: 500,
                height: 'auto',
                fluid: true,
                buttons: [
                    {
                        text: 'Confirm order',
                        click: function () {
                            $(this).closest('.ui-dialog-content').dialog('close');
                            removeAllItems()
                            var qtd = $(".cart-quantity").text()
                            if (qtd == "") {
                                $(".cart-quantity").text(1);
                            } else {
                                var intQtd = parseInt(qtd);
                                var newQtd = intQtd + 1;
                                $(".cart-quantity").text(newQtd);
                            }
                        }
                    },
                    {
                        text: 'Cancel',
                        click: function () {
                            $(this).closest('.ui-dialog-content').dialog('close');
                            removeAllItems()
                        }
                    }
                ]
            })

        }
    }).fail(function () {
        $("#erro").toggle();
        setTimeout(function () {
            $("#erro").toggle();
        }, 1500);
    })
        .always(function () {
            $("#spinner").toggle();
        });

}


function removeAllItems() {
    $("tbody>tr").remove();
}

function isDuplicated(itemName) {
    var hasDuplicated = false
    var lines = $("tbody>tr");
    $.each(lines, function () {
        var name = $(this).find("td:nth-child(2)").text();
        if (itemName == name) {
            hasDuplicated = true;
            return false;
        }
    })
    return hasDuplicated;
}

function addQuantityOnDuplicateItem(itemName, quantity) {
    var lines = $("tbody>tr");

    lines.each(function () {
        var name = $(this).find("td:nth-child(2)").text();
        var oldQtd = $(this).find("td:nth-child(3)").text();
        var intNewQtd = parseInt(quantity);
        var intOldQtd = parseInt(oldQtd);
        if (itemName == name) {
            $(this).find('td').eq(2).text(intNewQtd + intOldQtd);
        }
    })
}

function loadIngredients() {
    $("#spinner").toggle();

    $.get('http://'+window.location.hostname+'/snack-api/ingredients', function (ingredients) {
        $.each(ingredients, function (i, ingredient) {
            $('#ingredients').append('<option value="' + ingredient.id + '">' + ingredient.name + '</option>');
        });
    })
        .fail(function () {
            $("#erro").toggle();
            setTimeout(function () {
                $("#erro").toggle();
            }, 1500);
        })
        .always(function () {
            $("#spinner").toggle();
        });

}

function insertIngredient() {
    $("#msg").text("");
    var tableBody = $(".selected-ingredients").find("tbody");
    var ingredient = $("#ingredients :selected").text();
    var id = $("#ingredients").val();
    var quantity = $(".ingredient-quantity").val();

    var line = newLine(ingredient, quantity, id);
    line.find(".button-remove").click(removeLine);

    if (quantity == "" || quantity <= 0) {
        $("#msg").text("Field quantity is required :)");
    } else {
        if (isDuplicated(ingredient)) {
            addQuantityOnDuplicateItem(ingredient, quantity)
        } else {
            tableBody.append(line);
        }
    }
}

function newLine(ingredient, quantity, id) {
    var line = $("<tr>");
    var columnName = $("<td>").text(ingredient);
    var id = $("<td>").text(id);
    var columnQuantity = $("<td>").text(quantity);
    var columnRemove = $("<td>");

    var link = $("<a>").addClass("button-remove").attr("href", "#");

    var img = $('<img src="img/remove.png" id="remove-img">');
    img.appendTo(link);

    columnRemove.append(link);

    line.append(id);
    line.append(columnName);
    line.append(columnQuantity);
    line.append(columnRemove);

    return line;
}

function removeLine() {
    event.preventDefault();
    var line = $(this).parent().parent();
    line.fadeOut(1000);
    setTimeout(function () {
        line.remove();
    }, 1000);
}
