let selectedInterests = [];

function selectGender(gender) {
    document.querySelectorAll('.gender-buttons button').forEach(function(button) {
        button.classList.remove('selected');
    });

    const selectedButton = document.getElementById(gender);
    selectedButton.classList.add('selected');
    document.getElementById('gender').value = gender;
}

function toggleAllCheckboxes() {
    const agreeAll = document.getElementById('agree-all').checked;
    document.querySelectorAll('.agree-checkbox').forEach(checkbox => {
        checkbox.checked = agreeAll;
    });
}

function selectInterest(button) {
    const interest = button.textContent;
    if (selectedInterests.includes(interest)) {
        selectedInterests = selectedInterests.filter(i => i !== interest);
        button.classList.remove('selected');
    } else {
        selectedInterests.push(interest);
        button.classList.add('selected');
    }
    document.getElementById('interests').value = selectedInterests.join(',');
}

document.getElementById('registerForm').addEventListener('submit', function(e) {
    if (selectedInterests.length === 0) {
        e.preventDefault();
        alert('최소 1가지 이상의 질환을 선택해주세요.');
    }
});

document.addEventListener('DOMContentLoaded', function() {
    const scrollBoxes = document.querySelectorAll('.scroll-box');
    scrollBoxes.forEach(function(box) {
        const boxHeight = box.scrollHeight;
        const boxMaxHeight = box.clientHeight;
        if (boxHeight > boxMaxHeight) {
            box.classList.add('scroll-active');
        }
    });
});

function toggleDiseaseButtons() {
    const buttonContainer = document.getElementById('interest-buttons');
    const currentDisplay = buttonContainer.style.display;
    buttonContainer.style.display = currentDisplay === 'none' || currentDisplay === '' ? 'block' : 'none';
}

function validateAndSubmit() {
    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();
    const confirmPassword = document.getElementById("confirm-password").value.trim();
    const gender = document.getElementById("gender").value.trim();
    const selectedDiseases = document.querySelectorAll("#interest-buttons button.selected");

    // 약관 체크박스들
    const agreeAge = document.getElementById("agree-age").checked;
    const agreeTerms = document.getElementById("agree-terms").checked;
    const agreePrivacy = document.getElementById("agree-privacy").checked;

    // 이름 확인
    if (name.length < 2) {
        alert("이름은 두 글자 이상 입력해주세요.");
        return;
    }

    // 이메일 형식 확인
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        alert("올바른 이메일 형식을 입력해주세요.");
        return;
    }

    // 비밀번호 확인
    if (!password || !confirmPassword) {
        alert("비밀번호를 입력해주세요.");
        return;
    }
    if (password !== confirmPassword) {
        alert("비밀번호가 일치하지 않습니다.");
        return;
    }

    // 성별, 질병 확인
    if (!gender || selectedDiseases.length === 0) {
        alert("모든 필수 정보를 입력해주세요.");
        return;
    }

    // 약관 동의 확인
    if (!agreeAge || !agreeTerms || !agreePrivacy) {
        alert("모든 필수 약관에 동의하셔야 합니다.");
        return;
    }

    // 조건 통과 → 페이지 이동
    document.getElementById('registerForm').submit();
}