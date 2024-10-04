document.addEventListener('DOMContentLoaded', function () {
    // Get the modal and form elements
    const modal = document.getElementById("postModal");
    const form = document.getElementById("postForm");
    const titleInput = document.getElementById("title");
    const contentInput = document.getElementById("content");
    const postIdInput = document.getElementById("postId");
    const submitBtn = document.getElementById("submitBtn");
    const modalTitle = document.getElementById("modal-title");

    // Get the button that opens the modal for creating a new post
    const createPostBtn = document.getElementById("create-post-btn");

    // Get the close button element
    const closeBtn = document.getElementsByClassName("close")[0];

    // Get the context path from the server
    const contextPath = document.getElementById("context-path").value;

    // When the user clicks "Create Post", open the modal for creating a new post
    createPostBtn.addEventListener('click', function () {
        // Reset the form for a new post
        form.reset();
        postIdInput.value = ""; // No post ID (new post)
        form.action = `${contextPath}/create-post`; // Set action to create post
        submitBtn.value = "Create Post"; // Set button text to "Create Post"
        modalTitle.innerText = "Create a New Post"; // Set modal title
        form.method = 'post';
        // Open the modal
        modal.style.display = "block";
    });

    // Handle the "Edit" button for updating posts
    document.querySelectorAll('.edit-btn').forEach(function (editBtn) {
        editBtn.addEventListener('click', function () {
            const postId = this.getAttribute('data-id');

            // Send an AJAX request to get the post data
            fetch(`${contextPath}/get-post?id=${postId}`)
                .then(response => response.json())
                .then(data => {
                    // Fill the form with the existing post data
                    postIdInput.value = data.id; // Set post ID for update
                    titleInput.value = data.title;
                    contentInput.value = data.content;
                    form.action = `${contextPath}/update-post`; // Set action to update post
                    form.method = 'PUT';
                    submitBtn.value = "Update Post"; // Set button text to "Update Post"
                    modalTitle.innerText = "Update Post"; // Set modal title

                    // Open the modal
                    modal.style.display = "block";
                })
                .catch(error => {
                    console.error("Error fetching post data:", error);
                });
        });
    });

    // Close the modal when the close button is clicked
    closeBtn.addEventListener('click', function () {
        modal.style.display = "none";
    });

    // Close the modal when clicking outside the modal content
    window.addEventListener('click', function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });
});
