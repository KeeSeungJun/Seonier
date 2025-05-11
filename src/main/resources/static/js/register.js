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


// function selectInterest(button) {
//     const interest = button.textContent;
//     if (selectedInterests.includes(interest)) {
//         selectedInterests = selectedInterests.filter(i => i !== interest);
//         button.classList.remove('selected');
//     } else {
//         selectedInterests.push(interest);
//         button.classList.add('selected');
//     }
//     document.getElementById('interests').value = selectedInterests.join(',');
// }
//
// document.getElementById('registerForm').addEventListener('submit', function(e) {
//     if (selectedInterests.length === 0) {
//         e.preventDefault();
//         alert('최소 1가지 이상의 질환을 선택해주세요.');
//     }
// });

// 1) 버튼 클릭 처리
function selectInterest(btn) {
    const isNone = btn.dataset.none === 'true';
    const allBtns = document.querySelectorAll('#interest-buttons button');

    if (isNone) {
        // “없음” 클릭: 모든 health 필드 N, 모든 버튼 선택 해제
        allBtns.forEach(b => {
            b.classList.remove('selected');
            const field = b.dataset.field;
            if (field) {
                document.getElementById(field).value = 'N';
            }
        });
        // “없음” 버튼만 selected
        btn.classList.add('selected');

    } else {
        // 일반 버튼 클릭 시 “없음” 해제
        const noneBtn = document.querySelector('#interest-buttons button[data-none="true"]');
        noneBtn.classList.remove('selected');

        // 해당 버튼 토글: 히든 필드 Y/N 변경
        const field = btn.dataset.field;
        const input = document.getElementById(field);
        const currentlyY = input.value === 'Y';

        // 선택 상태 반전
        input.value = currentlyY ? 'N' : 'Y';
        btn.classList.toggle('selected', !currentlyY);
        //console.log('input.value : ', input.value);
    }
}

// 2) 버튼에 이벤트 바인딩
document.querySelectorAll('#interest-buttons button')
    .forEach(btn => btn.addEventListener('click', () => selectInterest(btn)));


// 3) 폼 제출 전 검증
document.getElementById('registerForm').addEventListener('submit', function(e) {
    // health1~8 중 하나라도 Y 인지 확인
    let anySelected = false;
    for (let i = 1; i <= 8; i++) {
        if (document.getElementById(`USR_HEALTH${i}`).value === 'Y') {
            anySelected = true;
            break;
        }
    }
    if (!anySelected) {
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
function selectDisease(btn) {
    const isNone = btn.dataset.none === 'true';
    const allButtons = document.querySelectorAll('#interest-buttons button');

    if (isNone) {
        // "없음" 클릭: 나머지 모두 해제 + 값 N
        allButtons.forEach(b => {
            b.classList.remove('selected');
            const f = b.dataset.field;
            if (f) document.getElementById(f).value = 'N';
        });
        btn.classList.add('selected');
        return;
    }

    // 다른 버튼 클릭: "없음" 해제
    const noneBtn = document.querySelector('#interest-buttons button[data-none="true"]');
    noneBtn.classList.remove('selected');

    // 해당 버튼 토글
    const field = btn.dataset.field;
    const input = document.getElementById(field);
    if (btn.classList.contains('selected')) {
        btn.classList.remove('selected');
        input.value = 'N';
    } else {
        btn.classList.add('selected');
        input.value = 'Y';
    }
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

    //console.log('selectedDiseases:', selectedDiseases);
    console.log(
        '선택된 질환들 텍스트:',
        Array.from(selectedDiseases).map(btn => btn.textContent)
    );
    // 조건 통과 → 페이지 이동
    // document.getElementById('registerForm').submit();

    const formData = {
        name: name,
        email: email,
        password: password,
        confirm_password: confirmPassword,
        gender: gender,
        selected_diseases: selectedDiseases
    };

    //console.log('selectedDiseases:', selectedDiseases);
    //console.log('formData:', formData);




    fetch('/api/register', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(formData)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Data:', data)
            if (data.code === 200) {
                console.log('Success:', data.message)
                window.location.href = "/main?user_id=" + data.user_id;
            } else {
                alert(`[ ERROR ]: ${data.message}`)
            }
        })
        .catch(error => {
            console.log('Error:', error)
            alert(`[ ERROR ]: ${error}`)
        });
}