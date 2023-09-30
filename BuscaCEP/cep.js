var cep = prompt("Forneça o CEP");
var req = new XMLHttpRequest();
req.onloadend = function(){
    resp = req.responseText;
    resp_obj = JSON.parse(resp);
    console.log(resp_obj)
    alert(`O CEP ${resp_obj.cep} pertence à rua ${resp_obj.logradouro} do bairro ${resp_obj.bairro}, ${resp_obj.localidade}/${resp_obj.uf}`);
}
req.open("GET", `https://viacep.com.br/ws/${cep}/json/`);
req.send(null);