const log = document.getElementById("logger");

document.addEventListener("DOMContentLoaded", () => {
  setTimeout(() => {
    log.style.height = `${log.offsetHeight - 41}px`;
  }, 1000);
});

document.querySelector(".dot.red").addEventListener("click", () => {
  document.getElementById("top").classList.add("closed");
  log.classList.add("closed");
});

document.querySelector(".dot.amber").addEventListener("click", () => {
  log.classList.add("hidden");
});

document.querySelector(".dot.green").addEventListener("click", () => {
  log.classList.remove("hidden");
});
