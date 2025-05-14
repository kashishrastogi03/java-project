<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Product Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            padding: 20px;
            background: linear-gradient(to right, #dce35b, #45b649);
            color: #333;
        }
        .dashboard-heading {
            text-align: center;
            font-weight: 700;
            margin-bottom: 30px;
            font-size: 2rem;
            color: #343a40;
        }
        .product-img {
            width: 80px;
            height: auto;
            object-fit: contain;
            border: 1px solid #ccc;
            padding: 4px;
            background: white;
            border-radius: 6px;
        }
        .table td, .table th {
            vertical-align: middle !important;
            text-align: center;
        }
    </style>
</head>
<body>

<div class="container">

    <div class="d-flex justify-content-between align-items-center mb-4">
        <div class="dashboard-heading">Product Dashboard</div>
        <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#productModal" onclick="openAddModal()">+ Add Product</button>
    </div>

    <div class="table-responsive">
        <table class="table table-bordered table-striped table-hover">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Quantity</th>
                    <th>Size</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${products}">
                <tr id="row-${product.id}">
                    <td>${product.id}</td>
                    <td>${product.title}</td>
                    <td>${product.quantity}</td>
                    <td>${product.size}</td>
                    <td>${product.description}</td>
                    <td>&#8377; ${product.price}</td>
                    <td>
                    <button class="btn btn-primary btn-sm"
        onclick="openEditModal(${product.id}, '${product.title}', ${product.quantity}, ${product.size}, '${product.description}', ${product.price})">Edit</button>
                    
                          <button class="btn btn-danger btn-sm" onclick="deleteProduct(${product.id})">Delete</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- Add/Edit Modal -->
<div class="modal fade" id="productModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content p-3">
            <form id="productForm" method="post" enctype="multipart/form-data">


                <div class="modal-header">
                    <h5 class="modal-title" id="modalTitle">Add Product</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">


                    <div class="mb-3">
                        <label>Title</label>
                        <input type="text" name="title" id="productTitle" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label>Quantity</label>
                        <input type="number" name="quantity" id="productQuantity" class="form-control" required>
                    </div>

                    <div class="mb-3">
                        <label>Size</label>
                        <input type="number" name="size" id="productSize" class="form-control" required>
                    </div>

              

                    <div class="mb-3">
                        <label>Description</label>
                        <textarea name="description" maxlength="20" id="productDescription" class="form-control" rows="2"></textarea>
                    </div>

                    <div class="mb-3">
                        <label>Price</label>
                        <input type="number" name="price" id="productPrice" class="form-control" required>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="submit" class="btn btn-success" id="saveBtn">Save</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    function openAddModal() {
        document.getElementById("modalTitle").innerText = "Add Product";
        document.getElementById("productForm").action = "/product/add";

        // Remove the hidden input for ID if it exists
        const existingIdField = document.getElementById("productId");
        if (existingIdField) {
            existingIdField.remove();
        }

        document.getElementById("productTitle").value = "";
        document.getElementById("productQuantity").value = "";
        document.getElementById("productSize").value = "";
        document.getElementById("productDescription").value = "";
        document.getElementById("productPrice").value = "";

    }


   function openEditModal(id, title, quantity, size, description, price) {
       document.getElementById("modalTitle").innerText = "Edit Product";
       document.getElementById("productForm").action = "/product/edit";

       // Dynamically create hidden input if it doesn't exist
       let hiddenIdField = document.getElementById("productId");
       if (!hiddenIdField) {
           hiddenIdField = document.createElement("input");
           hiddenIdField.type = "hidden";
           hiddenIdField.name = "id";
           hiddenIdField.id = "productId";
           document.getElementById("productForm").appendChild(hiddenIdField);
       }
       hiddenIdField.value = id;

       document.getElementById("productTitle").value = title;
       document.getElementById("productQuantity").value = quantity;
       document.getElementById("productSize").value = size;
       document.getElementById("productDescription").value = description;
       document.getElementById("productPrice").value = price;


       const productModal = new bootstrap.Modal(document.getElementById('productModal'));
       productModal.show();
   }

    function deleteProduct(id) {
        Swal.fire({
            title: 'Are you sure?',
            text: "You want to delete this product?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#d33',
            cancelButtonColor: '#aaa',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                fetch('/product/delete/' + id, {
                    method: 'DELETE'
                }).then(response => {
                    if (response.ok) {
                        document.getElementById("row-" + id).remove();
                        Swal.fire('Deleted!', 'Product has been deleted.', 'success');
                    } else {
                        Swal.fire('Error!', 'Failed to delete product.', 'error');
                    }
                });
            }
        });
    }

     document.getElementById("productForm").addEventListener("submit", function (e) {
            e.preventDefault(); // prevent default form submission

            const form = e.target;
            const formData = new FormData(form);

            fetch(form.action, {
                method: 'POST',
                body: formData
            })
            .then(response => {
                if (response.ok) {
                    // Close the modal manually
                    const modal = bootstrap.Modal.getInstance(document.getElementById('productModal'));
                    modal.hide();

                    Swal.fire({
                        icon: 'success',
                        title: 'Success!',
                        text: form.action.includes('edit') ? 'Product updated successfully!' : 'Product added successfully!',
                        confirmButtonColor: '#28a745'
                    }).then(() => {
                        // Optional: refresh page or fetch updated table rows
                        location.reload();
                    });
                } else {
                    throw new Error('Failed to save product.');
                }
            })
            .catch(error => {
                Swal.fire('Error', error.message, 'error');
            });
        });
</script>

</body>
</html>
