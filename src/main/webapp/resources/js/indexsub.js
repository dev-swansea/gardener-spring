$(() => {
  $('.owl-carousel.slide-one-item').owlCarousel({
    center            : false,
    autoplayHoverPause: true,
    items             : 1,
    loop              : true,
    stagePadding      : 0,
    margin            : 0,
    smartSpeed        : 1500,
    autoplay          : true,
    pauseOnHover      : false,
    dots              : true,
    nav               : true,
    navText           : ['<i class="fa fa-light fa-arrow-left"></i>', '<i class="fa fa-light fa-arrow-right"></i>']
  })


//dropdown event start
  const dropdownItems = document.querySelectorAll('.dropdown-item');

  dropdownItems.forEach(function (item) {
    item.addEventListener('click', function () {
      const selectedValue = item.getAttribute('href').substring(1); // Remove the '#' character
      document.getElementById('search_param').value = selectedValue;
      document.querySelector('.dropdown-toggle').textContent = item.textContent;
    });
  });
//dropdown event end
});