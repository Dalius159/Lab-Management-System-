function MultiSelectTag(elementId, options = { shadow: false, rounded: true }) {
    const { tagColor = {} } = options;
    const colors = {
        textColor: tagColor.textColor || "#FF5D29",
        borderColor: tagColor.borderColor || "#FF5D29",
        bgColor: tagColor.bgColor || "#FFE9E2"
    };

    let selectElement = document.getElementById(elementId);
    let items = [...selectElement.options].map(opt => ({
        value: opt.value,
        label: opt.label,
        selected: opt.selected
    }));

    let container, wrapper, body, inputContainer, inputElement, inputBody, buttonContainer, button, dropdown, list;
    const parser = new DOMParser();

    function createElementFromString(str) {
        return parser.parseFromString(str, "text/html").body.firstChild;
    }

    function renderList(filterText = "") {
        list.innerHTML = "";
        items.forEach(item => {
            if (!filterText || item.label.toLowerCase().startsWith(filterText.toLowerCase())) {
                const listItem = document.createElement("li");
                listItem.innerHTML = `<input type='checkbox' class='input_checkbox'> ${item.label}`;
                listItem.dataset.value = item.value;
                const checkbox = listItem.querySelector(".input_checkbox");
                checkbox.checked = item.selected;
                list.appendChild(listItem);
            }
        });
    }

    function renderSelectedTags() {
        while (inputBody.children.length > 1) {
            inputBody.removeChild(inputBody.firstChild);
        }

        items.filter(item => item.selected).forEach(item => {
            const tag = document.createElement("div");
            tag.className = "item-container";
            tag.style.color = colors.textColor;
            tag.style.borderColor = colors.borderColor;
            tag.style.backgroundColor = colors.bgColor;

            const label = document.createElement("div");
            label.className = "item-label";
            label.textContent = item.label;
            label.dataset.value = item.value;

            const closeIcon = createElementFromString(`
                <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="item-close-svg">
                    <line x1="18" y1="6" x2="6" y2="18"></line>
                    <line x1="6" y1="6" x2="18" y2="18"></line>
                </svg>
            `);
            closeIcon.addEventListener("click", () => {
                item.selected = false;
                renderSelectedTags();
                renderList();
                triggerChange();
            });

            tag.appendChild(label);
            tag.appendChild(closeIcon);
            inputBody.insertBefore(tag, inputElement);
        });
    }

    function triggerChange() {
        const selectedValues = items.filter(item => item.selected);
        selectElement.value = selectedValues.map(item => item.value);
        if (options.onChange) {
            options.onChange(selectedValues);
        }
    }

    function setup() {
        selectElement.classList.add("hidden");

        container = document.createElement("div");
        container.className = "multi-select-tag";

        wrapper = document.createElement("div");
        wrapper.className = "wrapper";

        body = document.createElement("div");
        body.className = "body";
        if (options.shadow) body.classList.add("shadow");
        if (options.rounded) body.classList.add("rounded");

        inputContainer = document.createElement("div");
        inputContainer.className = "input-container";

        inputElement = document.createElement("input");
        inputElement.className = "input";
        inputElement.placeholder = options.placeholder || "Search...";

        inputBody = document.createElement("div");
        inputBody.className = "input-body";

        inputBody.appendChild(inputElement);
        inputContainer.appendChild(inputBody);
        body.appendChild(inputContainer);

        buttonContainer = document.createElement("div");
        buttonContainer.className = "btn-container";

        button = document.createElement("button");
        button.type = "button";
        const arrow = createElementFromString(`
            <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" fill="none" viewBox="0 0 24 24" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                <polyline points="18 15 12 21 6 15"></polyline>
            </svg>
        `);
        button.appendChild(arrow);
        buttonContainer.appendChild(button);
        body.appendChild(buttonContainer);

        wrapper.appendChild(body);

        dropdown = document.createElement("div");
        dropdown.className = "drawer hidden";
        if (options.shadow) dropdown.classList.add("shadow");
        if (options.rounded) dropdown.classList.add("rounded");

        list = document.createElement("ul");
        dropdown.appendChild(list);

        container.appendChild(wrapper);
        container.appendChild(dropdown);

        selectElement.parentNode.insertBefore(container, selectElement.nextSibling);

        renderList();
        renderSelectedTags();
        attachEvents();
    }

    function attachEvents() {
        button.addEventListener("click", () => {
            dropdown.classList.toggle("hidden");
            if (!dropdown.classList.contains("hidden")) {
                inputElement.focus();
            }
        });

        inputElement.addEventListener("input", e => {
            renderList(e.target.value);
        });

        list.addEventListener("click", e => {
            const value = e.target.closest("li")?.dataset.value;
            if (!value) return;

            const item = items.find(item => item.value === value);
            if (item) {
                item.selected = !item.selected;
                renderSelectedTags();
                renderList();
                triggerChange();
            }
        });

        window.addEventListener("click", e => {
            if (!container.contains(e.target)) {
                dropdown.classList.add("hidden");
            }
        });
    }

    setup();
}
