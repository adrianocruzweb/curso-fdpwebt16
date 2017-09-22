var app = new function() {
	this.usuarios = [];

	this.verEditar = false;
	this.itemEditar = -1;
	this.numero = -1;

	this.salvar = function() {

		var nome = document.getElementById('nome');
		var email = document.getElementById('email');
		var usuario = {
			id : this.itemEditar,
			nome : nome.value,
			email : email.value
		};

		if (!this.verEditar) {
			if (usuario) {
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function() {
					if (this.readyState == 4 && this.status == 200) {
						app.listarTodos();
					}
				}

				xhttp.open("POST", "usucontroller", true);
				xhttp.setRequestHeader("Content-type",
						"application/x-www-form-urlencoded");
				xhttp.send("nome=" + usuario.nome + "&email=" + usuario.email);
			}
		} else {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					app.listarTodos();
				}
			}

			params = "id=" + usuario.id + "&nome=" + usuario.nome + "&email="
					+ usuario.email;

			console.log(params);

			xhttp.open("PUT", "usucontroller?" + params, true);
			xhttp.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			xhttp.send();

			this.verEditar = false;
		}

		this.listarTodos();
		this.limparCampo();
		this.contador();
	}

	this.listarTodos = function() {

		var xhttp = new XMLHttpRequest();

		xhttp.onload = function() {
			
			console.log(xhttp.response);
			
			app.usuarios = JSON.parse(xhttp.response);

			var dados = '';

			for (i = 0; i < app.usuarios.length; i++) {
				dados += '<tr>';
				dados += '<td>' + app.usuarios[i].nome + '</td>';
				dados += '<td>' + app.usuarios[i].email + '</td>';
				dados += '<td><button onclick="app.apagar(' + i
						+ ')">excluir</button></td>';
				dados += '<td><button onclick="app.editar(' + i
						+ ')">editar</button></td>';
				dados += '</tr>';
			}

			app.contador();
			document.getElementById('usuarios').innerHTML = dados;
		}

		xhttp.open("GET", "usucontroller", true);
		xhttp.setRequestHeader("Content-type",
		"application/x-www-form-urlencoded");
		xhttp.send();
	}

	this.limparCampo = function() {
		document.getElementById('nome').value = "";
		document.getElementById('email').value = "";
	}

	this.apagar = function(i) {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				app.listarTodos();
			}
		}

		xhttp.open("DELETE", "usucontroller?id=" + app.usuarios[i].id, true);
		xhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xhttp.send();

		this.contador();
	}

	this.editar = function(i) {
		document.getElementById('nome').value = app.usuarios[i].nome;
		document.getElementById('email').value = app.usuarios[i].email;
		this.verEditar = true;
		this.itemEditar = app.usuarios[i].id;
	}

	this.contador = function() {
		this.numero = this.usuarios.length;
		var novoContador = document.getElementById('contador');
		novoContador.innerHTML = this.numero;
	}
}
app.listarTodos();

app.contador();