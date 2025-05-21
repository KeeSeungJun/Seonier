function toggleDisease(button) {
    button.classList.toggle('selected');
    const diseaseId = button.getAttribute('data-disease');
    const hiddenInput = document.getElementById(diseaseId);
    hiddenInput.value = button.classList.contains('selected') ? 'Y' : 'N';
}

function saveHealthInfo() {
    const healthData = {
        health1: document.getElementById('health1').value,
        health2: document.getElementById('health2').value,
        health3: document.getElementById('health3').value,
        health4: document.getElementById('health4').value,
        health5: document.getElementById('health5').value,
        health6: document.getElementById('health6').value,
        health7: document.getElementById('health7').value,
        health8: document.getElementById('health8').value
    };

    // API 엔드포인트로 데이터 전송
    fetch('/api/user/health', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(healthData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    })
    .then(data => {
        alert('건강 정보가 성공적으로 저장되었습니다.');
    })
    .catch(error => {
        console.error('Error:', error);
        alert('건강 정보 저장에 실패했습니다. 다시 시도해주세요.');
    });
} 