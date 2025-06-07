const API_URL = 'http://localhost:8080/bugs';

document.getElementById('bugForm').addEventListener('submit', async (e) => {
  e.preventDefault();
  const title = document.getElementById('title').value;
  const description = document.getElementById('description').value;

  await fetch(API_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ title, description, status: 'OPEN' })
  });

  document.getElementById('bugForm').reset();
  loadBugs();
});

async function loadBugs() {
  const res = await fetch(API_URL);
  const bugs = await res.json();
  const bugList = document.getElementById('bugList');
  bugList.innerHTML = '';
  bugs.forEach((bug) => {
    const li = document.createElement('li');
    li.innerHTML = `
      <strong>${bug.title}</strong> - ${bug.status}<br/>
      ${bug.description}<br/>
      <button onclick="resolveBug(${bug.id})">Mark Resolved</button>
    `;
    bugList.appendChild(li);
  });
}

async function resolveBug(id) {
  await fetch(`${API_URL}/${id}/resolve`, { method: 'PUT' });
  loadBugs();
}

loadBugs();
