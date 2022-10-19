const boxes = document.querySelectorAll(".box");

window.addEventListener("scroll", checkBoxes);
checkBoxes();

function checkBoxes() {
  const bottomBox = (window.innerHeight / 5) * 5;

  boxes.forEach((box) => {
    const boxTop = box.getBoundingClientRect().top;

    if (boxTop < bottomBox) {
      box.classList.add("show");
    } else {
      box.classList.remove("show");
    }
  });
}
