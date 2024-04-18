


const scrollRevealOption = {
    distance: "50px",
    origin: "bottom",
    duration: 1000,
};

ScrollReveal().reveal(".header__container .section__subheader", {
    ...scrollRevealOption,
});
ScrollReveal().reveal(".header__container .section__header", {
    ...scrollRevealOption,
    delay: 500,
});
ScrollReveal().reveal(".header__container .scroll__btn", {
    ...scrollRevealOption,
    delay: 1000,
});
ScrollReveal().reveal(".header__container .header__socials", {
    ...scrollRevealOption,
    origin: "left",
    delay: 1500,
});

ScrollReveal().reveal(".about__image-1, .about__image-3", {
    ...scrollRevealOption,
    origin: "right",
});
ScrollReveal().reveal(".about__image-2", {
    ...scrollRevealOption,
    origin: "left",
});
ScrollReveal().reveal(".about__content .section__subheader", {
    ...scrollRevealOption,
    delay: 500,
});
ScrollReveal().reveal(".about__content .section__header", {
    ...scrollRevealOption,
    delay: 1000,
});
ScrollReveal().reveal(".about__content p", {
    ...scrollRevealOption,
    delay: 1500,
});
ScrollReveal().reveal(".about__content .about__btn", {
    ...scrollRevealOption,
    delay: 2000,
});