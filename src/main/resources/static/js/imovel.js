let cep = document.getElementById('endereco');

IMask(cep, {
    mask: '00000-000'
});

cep.addEventListener('blur', function () {
    let cep = this.value.replace(/\D/g, '');
    if (cep != "") {
        let validacep = /^[0-9]{8}$/;
        if (validacep.test(cep)) {
            document.getElementById('endereco').value = "Carregando dados..."
            fetch(`https://viacep.com.br/ws/${cep}/json/`)
                .then(response => response.json())
                .then(data => {
                    document.getElementById('logradouro').value = data.logradouro ? data.logradouro : "Não informado";
                    document.getElementById('complemento').value = data.complemento ? data.complemento : "Não informado";
                    document.getElementById('localidade').value = data.localidade ? data.localidade : "Não informado";
                    document.getElementById('uf').value = data.uf ? data.uf : "Não informado";
                    document.getElementById('bairro').value = data.bairro ? data.bairro : "Não informado";
                    document.getElementById('ddd').value = data.ddd ? data.ddd : "Não informado";
                    document.getElementById('endereco').value = cep ? cep : "Não informado";
                })
        }
    }
})