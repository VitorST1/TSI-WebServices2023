// var cep = prompt("Forneça o CEP");
// var req = new XMLHttpRequest();
// req.onloadend = function(){
//     resp = req.responseText;
//     resp_obj = JSON.parse(resp);
//     console.log(resp_obj)
//     alert(`O CEP ${resp_obj.cep} pertence à rua ${resp_obj.logradouro} do bairro ${resp_obj.bairro}, ${resp_obj.localidade}/${resp_obj.uf}`);
// }
// req.open("GET", `https://viacep.com.br/ws/${cep}/json/`);
// req.send(null);

const tableWrapper = document.querySelector("#tableWrapper")

function buscaCep() {
    tableWrapper.innerHTML = "Carregando..."
    const uf = document.querySelector("#uf").value
    const localidade = document.querySelector("#localidade").value.replace(" ","+")
    const logradouro = document.querySelector("#logradouro").value
    const req = new XMLHttpRequest();

    req.onloadend = function(){
        tableWrapper.innerHTML = ""
        resp = req.responseText

        if(!resp) {
            tableWrapper.innerHTML = "Ocorreu um erro!"
            return
        }

        resp_obj = JSON.parse(resp)

        if(resp_obj.length) {
            const table = document.createElement("table")
            table.classList.add("table")
            table.setAttribute("border", "1")
            table.style = "width: 100%;"
    
            const trHead = document.createElement("tr")
    
            const thBairro = document.createElement("th")
            thBairro.textContent = "Bairro"
    
            const thCep = document.createElement("th")
            thCep.textContent = "CEP"
    
            const thRua = document.createElement("th")
            thRua.textContent = "Rua"
    
            trHead.appendChild(thCep)
            trHead.appendChild(thBairro)
            trHead.appendChild(thRua)
            table.appendChild(trHead)

            for (const data of resp_obj.sort((a, b) => a.cep.localeCompare(b.cep))) {
                const tr = document.createElement("tr")
    
                const tdCep = document.createElement("td")
                tdCep.textContent = data.cep
    
                const tdBairro = document.createElement("td")
                tdBairro.textContent = data.bairro
    
                const tdRua = document.createElement("td")
                tdRua.textContent = data.logradouro
    
                tr.appendChild(tdCep)
                tr.appendChild(tdBairro)
                tr.appendChild(tdRua)
                table.appendChild(tr)
            }
            tableWrapper.appendChild(table)
        } else {
            tableWrapper.innerHTML = "CEP não encontrado"
        }
    }   

    req.open("GET", `https://viacep.com.br/ws/${uf}/${localidade}/${logradouro}/json/`);
    req.send(null);
}