async function cargarProductos() {
    try {
        const response = await fetch('http://localhost:8080/product');
        if (!response.ok) {
            throw new Error('No se pudo obtener los datos');
        }

        const productos = await response.json();

        const tablaBody = document.querySelector('#productosTable tbody');

        tablaBody.innerHTML = '';
        productos.forEach(producto => {
            const row = document.createElement('tr');
            
            row.innerHTML = `
                <td>${producto.id}</td>
                <td>${producto.nombre}</td>
                <td>${producto.precio}</td>
                <td>${new Date(producto.createdAt).toLocaleString()}</td>
                <td>${new Date(producto.updatedAt).toLocaleString()}</td>
            `;
            tablaBody.appendChild(row);
        });

    } catch (error) {
        console.error('Error al cargar los productos:', error);
    }
}

cargarProductos();