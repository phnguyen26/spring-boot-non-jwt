const API_URL = 'http://localhost:8081/building';

document.getElementById('searchForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    await searchBuildings();
});

async function searchBuildings() {
    const form = document.getElementById('searchForm');
    const formData = new FormData(form);
    
    // Build query parameters
    const params = new URLSearchParams();
    
    // Handle checkboxes separately
    const typeCodes = [];
    const checkboxes = form.querySelectorAll('input[name="typeCodes"]:checked');
    checkboxes.forEach(checkbox => {
        typeCodes.push(checkbox.value);
    });
    
    for (let [key, value] of formData.entries()) {
        // Skip typeCodes as we handle them separately
        if (key === 'typeCodes') continue;
        
        if (value && value.trim() !== '') {
            params.append(key, value.trim());
        }
    }
    
    // Add typeCodes to params
    typeCodes.forEach(code => {
        params.append('typeCodes', code);
    });
    
    // Show loading
    showLoading();
    hideError();
    clearResults();
    
    try {
        const url = params.toString() ? `${API_URL}?${params.toString()}` : API_URL;
        const response = await fetch(url);
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }
        
        const data = await response.json();
        displayResults(data);
    } catch (error) {
        console.error('Error searching buildings:', error);
        showError('Failed to fetch buildings. Please make sure the server is running on port 8081.');
    } finally {
        hideLoading();
    }
}

function displayResults(buildings) {
    const resultsContainer = document.getElementById('results');
    const resultCount = document.getElementById('resultCount');
    
    if (!Array.isArray(buildings) || buildings.length === 0) {
        resultsContainer.innerHTML = `
            <div class="no-results">
                <div class="no-results-icon">🏢</div>
                <p>No buildings found matching your search criteria.</p>
            </div>
        `;
        resultCount.textContent = '(0 results)';
        return;
    }
    
    resultCount.textContent = `(${buildings.length} result${buildings.length > 1 ? 's' : ''})`;
    
    resultsContainer.innerHTML = buildings.map(building => `
        <div class="building-card">
            <h3>🏢 ${escapeHtml(building.name || 'N/A')}</h3>
            <p><span class="label">📍 Address:</span> ${escapeHtml(building.address || 'N/A')}</p>
            <p><span class="label">🏗️ Basements:</span> ${building.numberOfBasement !== null && building.numberOfBasement !== undefined ? building.numberOfBasement : 'N/A'}</p>
            <p><span class="label">👤 Manager:</span> ${escapeHtml(building.managerName || 'N/A')}</p>
        </div>
    `).join('');
}

function showLoading() {
    document.getElementById('loading').style.display = 'block';
}

function hideLoading() {
    document.getElementById('loading').style.display = 'none';
}

function showError(message) {
    const errorDiv = document.getElementById('error');
    errorDiv.textContent = message;
    errorDiv.style.display = 'block';
}

function hideError() {
    document.getElementById('error').style.display = 'none';
}

function clearResults() {
    document.getElementById('results').innerHTML = '';
    document.getElementById('resultCount').textContent = '';
}

function resetForm() {
    document.getElementById('searchForm').reset();
    clearResults();
    hideError();
}

function escapeHtml(text) {
    if (!text) return '';
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}

// Load all buildings on page load
window.addEventListener('load', () => {
    searchBuildings();
});
