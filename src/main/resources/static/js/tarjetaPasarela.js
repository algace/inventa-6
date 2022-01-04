const ID_CHECKBOX_TARJETA = "#checkboxTarjeta";


var rowElement = null;
var idElement  = null;
var rowNode = null;

var cont = -1;
// Configuración de la tabla del popup para seleccionar Versiones
var tabla_checkboxTarjeta =  $(ID_CHECKBOX_TARJETA).DataTable({
	select: 'single',
	dom: '<"top">rt<"bottom"ipl><"clear">',
	data: JSON.parse(chasisPasarelasJson),
	searching:  true,
	columnDefs: [{ 
		targets: 0,
        render: function(data, type, full, meta){
		   if (type == "display"){
			   cont++;
			   var checked = data == true ? 'checked' : '';
	           return '<input type="checkbox" id="chasisPasarelas' + cont +'.isSeleccionado" name="chasisPasarelas['+ cont + '].isSeleccionado" ' + checked + '>' +
	           		  '<input type="hidden" id="chasisPasarelas' + cont +'.id" name="chasisPasarelas['+ cont + '].id" value="' + full.id + '">';
           }else{
			   return data;
		   }
        }
    }],
	columns: [
	  {data: "isSeleccionado", name: "seleccionar", title: "Seleccionar"},
	  {data: "nombre", name: "nombre", title: "Nombre"}
	],
	language: {
	    'sProcessing':     'Procesando...',
	    'sLengthMenu':     'Mostrar _MENU_ registros',
	    'sZeroRecords':    'No se encontraron resultados',
	    'sEmptyTable':     'Ningún dato disponible en esta tabla',
	    'sInfo':           'Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros',
	    'sInfoEmpty':      'Mostrando registros del 0 al 0 de un total de 0 registros',
	    'sInfoFiltered':   '(filtrado de un total de _MAX_ registros)',
	    'sInfoPostFix':    '',
	    'sSearch':         'Buscar:',
	    'sUrl':            '',
	    'sInfoThousands':  ',',
	    'sLoadingRecords': 'Cargando...',
	    'oPaginate': {
	        'sFirst':    '<<',
	        'sLast':     '>>',
	        'sNext':     '>',
	        'sPrevious': '<'
	    },
	    'oAria': {
	        'sSortAscending':  ': Activar para ordenar la columna de manera ascendente',
	        'sSortDescending': ': Activar para ordenar la columna de manera descendente'
	    },
	    select: {
            rows: ''
        }
	}
 })
 .on('click', 'tr', function() {
	 rowNode = this;
	 rowElement = tabla_checkboxTarjeta.row(this).data();
	 idElement = tabla_checkboxTarjeta.row(this).data()[0];
});
