(function($){
  $(function(){

    $('.sidenav').sidenav();

  }); // end of document ready
})(jQuery); // end of jQuery name space

$(document).ready(function(){
    $('.datepicker').datepicker({
        i18n: {
            months: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
            monthsShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
            weekdays: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sabádo'],
            weekdaysShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
            weekdaysAbbrev: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S'],
            today: 'Hoje',
            clear: 'Limpar',
            cancel: 'Sair',
            done: 'Confirmar',
            labelMonthNext: 'Próximo mês',
            labelMonthPrev: 'Mês anterior',
            labelMonthSelect: 'Selecione um mês',
            labelYearSelect: 'Selecione um ano',
            selectMonths: true,
            selectYears: 15,
        },
        format: 'dd/mm/yyyy',
        formatsubmit: 'dd/mm/yyyy',
        container: 'body',
        minDate: new Date(),
    });
});

$(document).ready(function(){
    $('.tooltipped').tooltip();
});

$(document).ready(function() {
	$('.modal').modal();
});

$(document).ready(function(){
    $('select').formSelect();
    $("select[required]").css({position: 'absolute', display: 'inline', height: 0, padding: 0, width: 0});
});

// Dropdown NavBar
$('.dropdown-trigger').dropdown({
    coverTrigger: false
});













