// Función para controlar que en un campo solo se puedan introducir números
function controlarValorEnteroPositivo(evento) {
	
	// se obtiene el código ASCII del caracter introducido
    var codigo = (evento.which) ? evento.which : evento.keyCode;
    
    if(codigo==8) { // borrar
      return true;
    } else if(codigo >= 48 && codigo <= 57) { // es un número
      return true;
    } else{ // otra tecla
      return false;
    }
}
