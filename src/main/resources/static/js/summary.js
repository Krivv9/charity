document.addEventListener("DOMContentLoaded", function() {

    var formElementsText = $('form#donation .group1');

    formElementsText.each(function () {
        $(this).on('keyup', function () {
            var liField = $(this).attr("data-liId");
            document.querySelector(liField).innerHTML = this.value;
        })
    });

    let formDate = $('form#donation .group2');
    formDate.each(function () {
        $(this).on('change', function () {
            var liField = $(this).attr("data-liId");
            document.querySelector(liField).innerHTML = this.value;
        })
    });

    $('form#donation .group3').on('blur' + '', function () {
        let liField = $(this).attr("data-liId");
        console.log(this.value);

        if (parseInt(this.value) > 1 && (this.value) < 5) {
            document.querySelector(liField).innerHTML = this.value + ' worki ';
        } else if (parseInt(this.value) >= 5) {
            document.querySelector(liField).innerHTML = this.value + ' worków ';
        } else {
            document.querySelector(liField).innerHTML = '1 worek ';
        }

    });

    let formInstitution = $('form#donation .group4');

    formInstitution.on('change', function () {
        for (let i = 0; i < formInstitution.length; i++) {
            if (formInstitution[i].checked) {
                console.log($(this).parent().find('.title').text());
                var liField = $(this).attr("data-liId");
                document.querySelector(liField).innerHTML = 'Dla fundacji ' + $(this).parent().find('.title').text();
            }
        }
    });

    var categorySpan = document.querySelector('#summaryCategory');

    var formCategory = document.querySelectorAll('input[type="checkbox"]');

    function updateCategory() {
        var categories = "";
        formCategory.forEach(function (element) {
            if(element.checked){
                categories += $(element).parent().find('.description').text() + " ";
            }
        });
        categorySpan.innerHTML = "z kategorii " + categories.toString();
    }

    for(var i = 0; i < formCategory.length; i++){
        formCategory[i].addEventListener('click', updateCategory);
    }

});