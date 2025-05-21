function toggleDisease(button) {
    button.classList.toggle('selected');
    const diseaseId = button.getAttribute('data-disease');
    const hiddenInput = document.getElementById(diseaseId);
    hiddenInput.value = button.classList.contains('selected') ? 'Y' : 'N';
}

document.querySelector('.contact-form').addEventListener('submit', function(e) {
    e.preventDefault();
    
    // 폼 데이터 수집
    const formData = {
        jobTitle: document.getElementById('jobTitle').value,
        location: document.getElementById('location').value,
        salary: document.getElementById('salary').value,
        companyName: document.getElementById('companyName').value,
        companyContact: document.getElementById('companyContact').value,
        healthRestrictions: {
            health1: document.getElementById('health1').value,
            health2: document.getElementById('health2').value,
            health3: document.getElementById('health3').value,
            health4: document.getElementById('health4').value,
            health5: document.getElementById('health5').value,
            health6: document.getElementById('health6').value,
            health7: document.getElementById('health7').value,
            health8: document.getElementById('health8').value
        }
    };

    // TODO: 서버로 데이터 전송
    console.log('Form data:', formData);
    
    // 성공 메시지 표시
    alert('일자리가 등록되었습니다.');
    
    // 메인 페이지로 리다이렉트
    window.location.href = 'main.html';
}); 