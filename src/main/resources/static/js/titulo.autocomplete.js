var Financeiro = Financeiro || {};

Financeiro.Autocomplete = (function () {

    function Autocomplete () {

    }

    Autocomplete.prototype.iniciar = function () {

        $.ajax({
            type: 'get',
            url:  '/entidades/filtro?nome=',
            success: function (response) {
                var entidadesServidor = response;
                var entidadesSugestao = {};

                for (var i = 0; i < entidadesServidor.length; i++){
                    entidadesSugestao[entidadesServidor[i].nome] = entidadesServidor[i].flags;
                }

                $('input.autocomplete').autocomplete({
                    data: entidadesSugestao,
                    limit: 6,
                    onAutocomplete: function(texto) {
                        onSendItem(texto);
                    },
                    minLength: 2
                });

                function onSendItem(texto) {
                    var codigo = 0;
                    entidadesServidor.filter(function (obj) {
                        if(obj.nome === texto){
                            codigo = obj.codigo;
                        }
                    });

                    if(codigo > 0){
                        console.log('codigo', codigo);
                        $('#entidade').attr('value', codigo);
                    }
                }

            }

        });
    };

    return Autocomplete;

}());

$(function () {

    var autocompletar = new Financeiro.Autocomplete();
    autocompletar.iniciar();


});