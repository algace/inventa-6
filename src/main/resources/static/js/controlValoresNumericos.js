// Función para controlar que en un campo solo se puedan introducir números enteros positivos
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


// Función para controlar que en un campo solo se puedan introducir números enteros positivos o negativos
function controlarValorEntero(evento) {
	
	// se obtiene el código ASCII del caracter introducido
    var codigo = (evento.which) ? evento.which : evento.keyCode;
    
    if(codigo==8) { // borrar
      return true;
    } else if(codigo >= 48 && codigo <= 57) { // es un número
      return true;
    } else if(codigo==45) { // es un -. Se controlará que solo pueda ser el primer carácter
	  var valorActual = evento.currentTarget.value;
	  if (valorActual=="") {
		return true;
	  } else {
	    return false;
	  }
    } else{ // otra tecla
      return false;
    }
}
