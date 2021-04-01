const hamburger = document.querySelector(".hamburger");
const navigation = document.querySelector(".navigation");

const handClick = () => {
    hamburger.classList.toggle("hamburger--active");
    navigation.classList.toggle("navigation--active")
}

hamburger.addEventListener('click', handClick);