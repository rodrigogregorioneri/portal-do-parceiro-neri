(function ($app) {
    
  /**
   * Função que retorna o formato que será utilizado no componente
   * capturando o valor do atributo format do elemento, para mais formatos
   * consulte os formatos permitidos em http://momentjs.com/docs/#/parsing/string-format/
   * 
   */
  var patternFormat = function(element){
    if(element){
      return $(element).attr('format') || 'DD/MM/YYYY';
    }
    return 'DD/MM/YYYY';
  }
  
  /**
   * Em todo elemento que possuir o atibuto as-date será 
   * aplicado o componente Datetimepicker (http://eonasdan.github.io/bootstrap-datetimepicker/)
   * 
   * O componente se adequa de acordo com o formato, definido através do atributo format
   * espeficado no elemento.
   * Para data simples use format="DD/MMM/YYYY", para data e hora use format="DD/MM/YYYY HH:mm:ss"
   * 
   * @see http://eonasdan.github.io/bootstrap-datetimepicker/
   */
  app.directive('asDate', function () {
      return {
          require: '^ngModel',
          restrict: 'A',
          link: function (scope, element, attrs, ngModel) {
              if(!ngModel){
                return;
              } 
              
              var format = patternFormat(element);
              
              var options = {
                format: format,
                locale : 'pt-BR',
                showTodayButton: true,
                useStrict: true,
                tooltips: {
                  today: 'Hoje',
                  clear: 'Limpar seleção',
                  close: 'Fechar',
                  selectMonth: 'Selecionar mês',
                  prevMonth: 'Mês anterior',
                  nextMonth: 'Próximo mês',
                  selectYear: 'Selecionar ano',
                  prevYear: 'Ano anterior',
                  nextYear: 'Próximo ano',
                  selectDecade: 'Selecionar década',
                  prevDecade: 'Década anterior',
                  nextDecade: 'Próxima década',
                  prevCentury: 'Século anterior',
                  nextCentury: 'Próximo século'
                }
              };
              
              if(format != 'DD/MM/YYYY'){
                options.sideBySide = true;
              }
              
              element.datetimepicker(options);
  
              element.on('dp.change', function(){
                  scope.$apply(read);
              });
              
              ngModel.$render = function(){
                if(ngModel.$viewValue){
                  var momentDate = moment(ngModel.$viewValue);
                  if(momentDate.isValid()){
                    element.val( momentDate.format(patternFormat(element)));
                  }else{
                    element.val('');
                  }
                }else{
                  element.data("DateTimePicker").clear();
                  element.val('');
                }
              }
  
              read();
  
              function read() {
                  var value = element.val();
                  var momentDate = moment(value, patternFormat(element));
                  if(momentDate.isValid())
                    ngModel.$setViewValue(momentDate.toDate());
              }
          }
      };
  })
  
  .directive('pwCheck', [function () { 'use strict';
    return {
      require: 'ngModel',
      link: function (scope, elem, attrs, ctrl) {
      var firstPassword = '#' + attrs.pwCheck;
      elem.add(firstPassword).on('keyup', function () {
        scope.$apply(function () {
        var v = elem.val()===$(firstPassword).val();
        ctrl.$setValidity('pwmatch', v);
        });
      });
      }
    }
  }])
} (app));