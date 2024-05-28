let reservedSeatsData = document.getElementById('reservedSeats')
    .dataset.reservedSeats
    .replace("[", "")
    .replace("]", "")
    .replace(/"/g, '').split(',');

var button = document.getElementById('reservation-button');
button.disabled = true;
const typeHall = document.getElementById('typeHall').dataset.typehall;
let COLORS = ['#FF6347', '#008B8B', '#228B22', '#00BFFF', '#9370DB']
console.log(document.getElementById('priceSeats').dataset.priceSeats);
let priceSeats = document.getElementById('priceSeats')
    .dataset.priceSeats.replace("[", '')
    .replace("]", '')
    .replace(/"/g, '')
    .split(",");
let priceId = document.getElementById('priceId')
    .dataset.priceid.replace("[", '').replace("]", '').split(",");


let selectedSeats = [];
document.addEventListener('DOMContentLoaded', function () {
    const cinemaHall = document.getElementById('cinema-hall');
    if (typeHall === '1') {
        createSeats();
    } else if (typeHall === '2') {
        createSeatsSecondHall();
    }

    function createSeats() {
        const size = 15;
        const yCoord = 25;
        // Создаем места в зале
        // левый  верхний
        createSideSeats(200, 30, 3, 26, 24, 0);
        createSideSeats(200 - size, 30 + yCoord, 4, 25, 26, 0);
        createSideSeats(200 - 3 * size, 30 + yCoord * 2, 9, 24, 43, 0);
        createSideSeats(200 - 4 * size, 30 + yCoord * 3, 11, 23, 45, 0);
        createSideSeats(200 - 5 * size, 30 + yCoord * 4, 12, 22, 47, 1);
        createSideSeats(200 - 6 * size, 30 + yCoord * 5, 13, 21, 49, 1);
        createSideSeats(200 - 6 * size, 30 + yCoord * 6, 13, 20, 49, 1);
        createSideSeats(200 - 6 * size, 30 + yCoord * 7, 13, 19, 49, 1);


        for (let i = 0; i < 7; i++) {
            createSideSeats(200 - size, 30 + yCoord * (8 + i), 8, (18 - i), 39, 2);
        }

        // центральный
        createSideSeats(200 + 9 * size, 30 + yCoord * 2, 24, 24, 33, 0);
        createSideSeats(200 + 9 * size, 30 + yCoord * 3, 24, 23, 34, 1);
        createSideSeats(200 + 9 * size, 30 + yCoord * 4, 24, 22, 35, 1);
        for (let i = 0; i < 3; i++) {
            createSideSeats(200 + 9 * size, 30 + yCoord * (5 + i), 24, 21 - i, 36, 1);
        }
        for (let i = 0; i < 7; i++) {
            createSideSeats(200 + 9 * size, 30 + yCoord * (8 + i), 24, 18 - i, 31, 2);
        }

        // правый верхний
        createSideSeats(200 + 38 * size, 30, 3, 26, 3, 0);
        createSideSeats(200 + 38 * size, 30 + yCoord, 4, 25, 4, 0);
        createSideSeats(200 + 35 * size, 30 + yCoord * 2, 9, 24, 9, 0);
        createSideSeats(200 + 35 * size, 30 + yCoord * 3, 10, 23, 10, 0);
        createSideSeats(200 + 35 * size, 30 + yCoord * 4, 11, 22, 11, 1);

        createSideSeats(200 + 35 * size, 30 + yCoord * 5, 12, 21, 12, 1);
        createSideSeats(200 + 35 * size, 30 + yCoord * 6, 12, 20, 12, 1);
        createSideSeats(200 + 35 * size, 30 + yCoord * 7, 12, 19, 12, 1);
        for (let i = 0; i < 7; i++) {
            createSideSeats(200 + 35 * size, 30 + yCoord * (8 + i), 7, 18 - i, 7, 2);
        }

        // левый нижний
        for (let i = 0; i < 5; i++) {
            createSideSeats(200 - size * 5, 30 + yCoord * (17 + i), 12, 11 - i, 47, 2);
        }
        for (let i = 0; i < 5; i++) {
            createSideSeats(200 - size * 5, 30 + yCoord * 5 + yCoord * (17 + i), 12, 7 - i, 43, 3);
        }
        createSideSeats(200 - size * 4, 30 + yCoord * 27, 11, 1, 42, 4);


        // центральный нижний
        for (let i = 0; i < 5; i++) {
            createSideSeats(200 + 9 * size, 30 + yCoord * (17 + i), 24, (11 - i), 35, 3);
        }
        for (let i = 0; i < 6; i++) {
            createSideSeats(200 + 9 * size, 30 + yCoord * (22 + i), 24, (6 - i), 31, 3);
        }
        createSideSeats(200 + 9 * size, 30 + yCoord * 27, 24, 1, 31, 4);

        // правый нижный
        for (let i = 0; i < 5; i++) {
            createSideSeats(200 + 35 * size, 30 + yCoord * (17 + i), 11, 11 - i, 11, 2);
        }

        for (let i = 0; i < 5; i++) {
            createSideSeats(200 + 35 * size, 30 + yCoord * (22 + i), 7, 6 - i, 7, 3);
        }
        createSideSeats(200 + 35 * size, 30 + yCoord * 27, 7, 1, 7, 4);

    }

    function createSeatsSecondHall() {
        const size = 25;
        const yCoord = 25;
        const xCoord = 100;

        // left
        createSideSeats(xCoord + size * 7, 0, 4, 25, 8, 0);
        createSideSeats(xCoord + size * 6, yCoord, 8, 24, 14, 0);
        createSideSeats(xCoord + size * 5, yCoord * 2, 9, 23, 18, 0);
        createSideSeats(xCoord + size * 2, yCoord * 3, 12, 22, 24, 1);
        createSideSeats(xCoord + size * 2, yCoord * 4, 12, 21, 24, 1);
        createSideSeats(xCoord, yCoord * 5, 14, 20, 28, 1);
        createSideSeats(xCoord + size, yCoord * 6, 13, 19, 26, 1);
        createSideSeats(xCoord + 2 * size, yCoord * 7, 12, 18, 24, 1);
        createSideSeats(xCoord, yCoord * 8, 14, 17, 28, 1);
        createSideSeats(xCoord + size, yCoord * 9, 13, 16, 26, 2);
        createSideSeats(xCoord + 2 * size, yCoord * 10, 12, 15, 24, 2);
        createSideSeats(xCoord + 2 * size, yCoord * 11, 12, 14, 24, 2);
        createSideSeats(xCoord, yCoord * 12, 14, 13, 28, 2);
        createSideSeats(xCoord + size, yCoord * 13, 13, 12, 26, 2);
        createSideSeats(xCoord + 2 * size, yCoord * 14, 12, 11, 24, 2);

        createSideSeats(xCoord, yCoord * 16, 14, 10, 28, 3);
        createSideSeats(xCoord + size, yCoord * 17, 13, 9, 26, 3);
        createSideSeats(xCoord + 2 * size, yCoord * 18, 12, 8, 24, 3);
        createSideSeats(xCoord + 2 * size, yCoord * 19, 12, 7, 24, 3);
        createSideSeats(xCoord + 2 * size, yCoord * 20, 12, 6, 24, 3);
        createSideSeats(xCoord + 2 * size, yCoord * 21, 12, 5, 24, 3);
        createSideSeats(xCoord + 2 * size, yCoord * 22, 10, 4, 24, 4);
        createSideSeats(xCoord + 2 * size, yCoord * 23, 10, 3, 24, 4);
        createSideSeats(xCoord + 2 * size, yCoord * 24, 12, 2, 24, 4);
        createSideSeats(xCoord + 2 * size, yCoord * 25, 12, 1, 24, 4);

        //right
        createSideSeats(xCoord + size * 18, yCoord, 2, 24, 6, 0);
        createSideSeats(xCoord + size * 18, yCoord * 2, 9, 23, 9, 0);
        createSideSeats(xCoord + size * 18, yCoord * 3, 12, 22, 12, 1);
        createSideSeats(xCoord + size * 18, yCoord * 4, 12, 21, 12, 1);
        createSideSeats(xCoord + size * 18, yCoord * 5, 14, 20, 14, 1);
        createSideSeats(xCoord + size * 18, yCoord * 6, 13, 19, 13, 1);
        createSideSeats(xCoord + size * 18, yCoord * 7, 12, 18, 12, 1);
        createSideSeats(xCoord + size * 18, yCoord * 8, 14, 17, 14, 1);
        createSideSeats(xCoord + size * 18, yCoord * 9, 13, 16, 13, 2);
        createSideSeats(xCoord + size * 18, yCoord * 10, 12, 15, 12, 2);
        createSideSeats(xCoord + size * 18, yCoord * 11, 12, 14, 12, 2);
        createSideSeats(xCoord + size * 18, yCoord * 12, 14, 13, 14, 2);
        createSideSeats(xCoord + size * 18, yCoord * 13, 13, 12, 13, 2);
        createSideSeats(xCoord + size * 18, yCoord * 14, 12, 11, 12, 2);
        createSideSeats(xCoord + size * 18, yCoord * 16, 14, 10, 14, 3);
        createSideSeats(xCoord + size * 18, yCoord * 17, 13, 9, 13, 3);
        createSideSeats(xCoord + size * 18, yCoord * 18, 12, 8, 12, 3);
        createSideSeats(xCoord + size * 18, yCoord * 19, 12, 7, 12, 3);
        createSideSeats(xCoord + size * 18, yCoord * 20, 12, 6, 12, 3);
        createSideSeats(xCoord + size * 18, yCoord * 21, 12, 5, 12, 3);
        createSideSeats(xCoord + size * 20, yCoord * 22, 10, 4, 10, 4);
        createSideSeats(xCoord + size * 20, yCoord * 23, 10, 3, 10, 4);
        createSideSeats(xCoord + size * 18, yCoord * 24, 12, 2, 12, 4);
        createSideSeats(xCoord + size * 18, yCoord * 25, 12, 1, 12, 4);
    }


    function createSideSeats(x, y, miniRectangleWidth, row, col, price) {
        if (typeHall === '2') {
            let count = 1;
            for (let i = 0; i < miniRectangleWidth; i++) {
                createSeat(x + i * 20 + count * 6, y + 20, row, col--, price);
                count += 1;

            }
        } else {
            for (let i = 0; i < miniRectangleWidth; i += 1) {
                createSeat(x + i * 15, y + 20, row, col--, price);
            }

        }

    }

    if (typeHall === '1') {
        const yPos = 25;
        for (i = 0; i < 15; i++) {
            createRowNum(70, 85 + yPos * i, 26 - i);
        }


        for (j = 0; j < 11; j++) {
            createRowNum(70, 85 + 17 * yPos + yPos * j, 11 - j);
        }
    } else if (typeHall === '2') {
        const yPos = 26;
        for (i = 0; i < 15; i++) {
            createRowNum(0, 50 + yPos * i, 25 - i);
        }


        for (j = 0; j < 10; j++) {
            createRowNum(0, 10 + 17 * yPos + yPos * j, 10 - j);
        }
    }

    function createRowNum(x, y, row) {
        const rowNumber = document.createElementNS("http://www.w3.org/2000/svg", 'text');
        rowNumber.setAttribute('x', x);
        rowNumber.setAttribute('y', y);
        rowNumber.setAttribute('fill', 'white');
        rowNumber.setAttribute('font-size', '13');
        rowNumber.setAttribute('font-family', "Arial, sans-serif");
        rowNumber.textContent = row;
        cinemaHall.appendChild(rowNumber);
    }

    const stageLabel = document.createElementNS("http://www.w3.org/2000/svg", 'text');
    stageLabel.setAttribute('x', 500);
    stageLabel.setAttribute('y', 800);
    stageLabel.setAttribute('fill', 'white');
    stageLabel.setAttribute('text-anchor', 'middle');
    stageLabel.setAttribute('font-size', '20');
    stageLabel.textContent = 'Сцена';
    cinemaHall.appendChild(stageLabel);


    function createSeat(x, y, row, col, price) {
        let seat = null;
        seat = document.createElementNS("http://www.w3.org/2000/svg", 'circle');
        seat.setAttribute('cx', x);
        seat.setAttribute('cy', y + 30);
        seat.setAttribute('data-coords', row + " " + col);
        seat.setAttribute('data-row', row);
        seat.setAttribute('data-col', col);
        seat.setAttribute('data-text', "");
        seat.setAttribute('price', priceSeats[price]);
        seat.setAttribute('priceId', priceId[price]);
        if (!reservedSeatsData.includes(row + " " + col)) {
            seat.setAttribute('fill', COLORS[price]);
        } else {
            seat.setAttribute('fill', 'grey');
        }
        if (typeHall === '1') {
            seat.setAttribute('r', '7');
        } else {
            seat.setAttribute('r', '10');
        }
        seat.classList.add('seat');
        cinemaHall.appendChild(seat);

        const tooltip = document.createElementNS("http://www.w3.org/2000/svg", 'rect');
        tooltip.setAttribute('x', x - 100);
        tooltip.setAttribute('y', y);
        tooltip.textContent = "";
        tooltip.setAttribute('width', '140');
        tooltip.setAttribute('height', '50');
        tooltip.setAttribute('rx', '10');
        tooltip.setAttribute('ry', '10');
        tooltip.setAttribute('fill', 'none');
        tooltip.setAttribute('text-anchor', 'middle');
        tooltip.style.color = 'blue';
        cinemaHall.appendChild(tooltip);

        const text = document.createElementNS("http://www.w3.org/2000/svg", 'text'); // Создание текстового элемента для подсказки
        text.setAttribute('x', x);
        text.setAttribute('y', y);
        text.setAttribute('text-anchor', 'middle');
        text.setAttribute('fill', 'white');
        text.setAttribute('font-size', '14');
        text.setAttribute('font-family', "Arial, sans-serif");
        text.textContent = '';
        const tspan1 = document.createElementNS("http://www.w3.org/2000/svg", 'tspan');
        tspan1.textContent = '';
        tspan1.setAttribute('x', x);
        tspan1.setAttribute('dy', '1.5em');
        tspan1.setAttribute('font-weight', 'bold');
        text.appendChild(tspan1);

        const tspan2 = document.createElementNS("http://www.w3.org/2000/svg", 'tspan');
        tspan2.textContent = '';
        tspan2.setAttribute('x', x);
        tspan2.setAttribute('dy', '1.2em');
        text.appendChild(tspan2);
        cinemaHall.appendChild(text);
        cinemaHall.appendChild(text);


        seat.addEventListener('mouseover', function (event) {
            if (!reservedSeatsData.includes(event.target.getAttribute("data-coords"))) {

                const mouseX = event.target.getAttribute("cx") - 70;
                const mouseY = event.target.getAttribute("cy") - 70;
                if (typeHall === '1') {
                    event.target.setAttribute('r', '10');
                } else {
                    event.target.setAttribute('r', '13');
                }

                tooltip.setAttribute('x', mouseX);
                tooltip.setAttribute('y', mouseY);
                tooltip.setAttribute('fill', 'grey');
                text.setAttribute('x', mouseX);
                text.setAttribute('y', mouseY);
                tspan1.setAttribute('x', mouseX + 70);

                tspan1.setAttribute('y', mouseY);

                tspan2.setAttribute('x', mouseX + 70);
                tspan2.textContent = event.target.getAttribute("data-row") + " ряд, " + event.target.getAttribute("data-col") + ' место';
                tspan1.textContent = event.target.getAttribute("price") + ' ₽';
                event.target.setAttribute('fill', '#483D8B');
                cinemaHall.appendChild(tooltip);
                cinemaHall.appendChild(text);
            }
        });

        seat.addEventListener('mouseout', function (event) {
            if (!reservedSeatsData.includes(event.target.getAttribute("data-coords"))) {
                if (typeHall === '1') {
                    event.target.setAttribute('r', '7');
                } else if (typeHall === '2') {
                    event.target.setAttribute('r', '10');
                }
                event.target.setAttribute('fill', COLORS[price]);
                tooltip.textContent = '';
                tooltip.remove();
                text.remove();
            }
        });

        seat.addEventListener('click', function (event) {
            const seatCoords = event.target.getAttribute("data-coords");
            const price = event.target.getAttribute("priceId");
            var button = document.getElementById('reservation-button');


            if (!reservedSeatsData.includes(seatCoords)) {
                const isSeatSelected = event.target.classList.contains('selected');
                if (isSeatSelected) {
                    const index = selectedSeats.indexOf(seatCoords + ":" + price);
                    if (index !== -1) {
                        selectedSeats.splice(index, 1);
                    }
                    if (selectedSeats.length === 0) {
                        button.disabled = true;
                    }
                    event.target.classList.toggle('selected');
                } else {
                    button.disabled = false;
                    selectedSeats.push(seatCoords + ':' + price);
                    event.target.classList.toggle('selected');
                    document.getElementById('selectedSeats').value = selectedSeats.join(',');
                }
            }
        });
    }

});
