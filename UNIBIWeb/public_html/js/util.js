function crearDatePicker(idform, idcontrol) {
    $(function() {
        $( "#" + idform + "\\:" + idcontrol ).datepicker({ showOn: "both", dateFormat: "dd-mm-yy",
            monthNames: [ "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" ],
            dayNamesMin: [ "Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab" ] });
        });			
}

function mostrarNotificacion(mensaje) {
    noty({text: mensaje,timeout:2000,layout:'bottom',theme: 'defaultTheme'}); 
}

function validar(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 8)
        return true;
    patron = /[A-Za-z\s]/;// patron = /\d/; // Solo acepta números
    te = String.fromCharCode(tecla);
    return patron.test(te);
}

function codigo(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla == 8)
        return true;
    patron = /\d/;
    te = String.fromCharCode(tecla);
    return patron.test(te);
}

function mostrarMensaje(mensaje, resultado) {
    if (resultado == 'true') {
        REDIPS.dialog.show(400, 120, mensaje, 'Aceptar');
    }
}

function renderPage(pagePath) {
    document.getElementById('frmBody').src = pagePath;
}

function disableControl(id) {
    document.getElementById('formulario:' + id).disabled = true;
}

function showCalendar(id) {
    NewCssCal('formulario:' + id,'yyyyMMdd','dropdown',true,'24')
}

function adaptarTablaAlternativa(idform, idcontrol) {
    $("#" + idform + "\\:" + idcontrol + " tr").not(':first').hover(
      function () {
        $(this).css("background","#9DC3E6");
      }, 
      function () {
        $(this).css("background","");
      }
    );
}
function validarNumero(numero) 
              {
                  if (!/^([0-9])*$/.test(numero)) {
                    
                      alert("El valor '" + numero + "' no es un numero");
                      nombre=document.getElementById("formulario:txtNroDocumentoUsuario");
                      nombre.value='';
                      return false;
                  }
                  return true;
              } 
