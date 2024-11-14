document.addEventListener('DOMContentLoaded', () => {

    const productoSelect = document.querySelector('#productoSelect');
    const desc = document.querySelector('#desc');
    const cant = document.querySelector('#cant');
    const WS_SERVICE = "http://localhost:8080/"
    const btnEnviar = document.querySelector("#btnSend");

    btnEnviar.addEventListener("click", SendData);

    async function SendData() {

        const datos = {
            "descripcion": desc.value,
            "cantidad": cant.value,
            "producto": {
                "id": productoSelect.value.split("|")[0],
                "nombre": productoSelect.value.split("|")[1],
                "precio": productoSelect.value.split("|")[2],
            }
        }

        console.log(datos)

        await fetch(WS_SERVICE + "detalle", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
        }).then(response => response.json())
        .then(data => {
          console.log('Respuesta del servidor:', data);
        })
        .catch(error => {
          console.error('Error en la solicitud:', error);
        });
    }


    fetch(WS_SERVICE + 'product')
        .then(response => response.json())
        .then(data => {
            data.forEach(producto => {
                console.log(producto)

                const option = document.createElement('option');
                option.value = producto.id + "|" + producto.nombre + "|" + producto.precio;
                option.textContent = producto.nombre + " - " + producto.precio;
                productoSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error al cargar los productos:', error);
        });
})