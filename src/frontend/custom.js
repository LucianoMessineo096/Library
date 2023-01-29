

/* caching del dom */
let ilbottonecaricatabella = document.querySelector("#bottonecaricatabella");
let ildivcaricatabella = document.querySelector("#tabellautenti");
let ilbottonesubmit = document.querySelector("#submitButton")

/* evento */
ilbottonecaricatabella.addEventListener("click",caricaAutori);
//ilbottonesubmit.addEventListener("click",inserisciAutore);

/* logica */
function inserisciAutore(){
    console.log("entrato");
    let campoID = parseInt(document.querySelector("#id").value);
    console.log(campoID)
    let campoNome = document.querySelector("#nome").value;
    let campoCognome = document.querySelector("#cognome").value;
    let campoNazionalita = document.querySelector("#nazionalita").value;
    
    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://localhost:8080/example/libreria/autori',true);
    xhr.setRequestHeader('Content-type', 'application/json','application/json;charset=UTF-8');

    /*
    let json = JSON.stringify({
        "idAutore": campoID,
        "nome": campoNome,
        "cognome": campoCognome, 
        "NazioneResidenza": campoNazionalita
    })
    */

    xhr.send(JSON.stringify({
        "idAutore": campoID,
        "nome": campoNome,
        "cognome": campoCognome, 
        "NazioneResidenza": campoNazionalita
    }));
}

function caricaAutori() {
    /* creiamo l'oggetto XMLHttpRequest() cioè la chiamata di js asincrono su http  */
    let xhr = new XMLHttpRequest();

    xhr.open('GET','http://localhost:8080/example/libreria/autori',true);

    /* impostiamo i parametri e le fasi della richiesta e ricezione della risposta */
    xhr.onload = function() {
        if (this.status === 200) {
            let rispostaserver = JSON.parse(this.responseText);

            /* 1 apertura tabella e intestazioni */
            let output = "<table class=\"table table-striped table-bordered\"><tr><td>ID</td><td>NOME</td><td>COGNOME</td><td>NAZIONALITA</td></tr>";

            /* 2 stampa righe dinamicamente */
            rispostaserver.forEach(function(autori) {
                output += "<tr><td>"   + autori.idAutore         + "</td>" +
                              "<td>"   + autori.nome             + "</td>" +
                              "<td>"   + autori.cognome          + "</td>" +
                              "<td>"   + autori.NazioneResidenza + "</td></tr>";
            });
            /* 3 chiusura tabella */
            output += "</table>";

            ildivcaricatabella.innerHTML = output;
        }
    }

    /* la cosa più importante, il send!!!! */
    xhr.send();
}