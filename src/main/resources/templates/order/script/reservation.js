// let reservedSeatsData = document.getElementById('reservedSeats').outerHTML;
let reservedSeatsData = document.getElementById('reservedSeats').dataset.reservedSeats.replace("[", '')
    .replace("]", "")
    .replace(/"/g, '').split(",");

console.log(reservedSeatsData);
let COLORS = ['#FF6347', '#008B8B', '#228B22', '#00BFFF', '#9370DB']
let priceSeats = document.getElementById('priceSeats').dataset.priceSeats.replace("[", '')
    .replace("]", '').replace(/"/g, '').split(",");
console.log(priceSeats);
let selectedSeats = [];
document.addEventListener('DOMContentLoaded', function () {
    const cinemaHall = document.getElementById('cinema-hall');
    createSeats();

    function createSeats() {
        const size = 15;
        const yCoord = 25;
        // Создаем места в зале
        // левый  верхний
        createSideSeats(200, 30, 3, 1, 26, 1, 0);
        createSideSeats(200 - size, 30 + yCoord, 4, 1, 25, 1, 0);
        createSideSeats(200 - 3 * size, 30 + yCoord * 2, 9, 1, 24, 1, 0);
        createSideSeats(200 - 4 * size, 30 + yCoord * 3, 11, 1, 23, 1, 0);
        createSideSeats(200 - 5 * size, 30 + yCoord * 4, 12, 1, 22, 1, 1);
        createSideSeats(200 - 6 * size, 30 + yCoord * 5, 13, 1, 21, 1, 1);
        createSideSeats(200 - 6 * size, 30 + yCoord * 6, 13, 1, 20, 1, 1);
        createSideSeats(200 - 6 * size, 30 + yCoord * 7, 13, 1, 19, 1, 1);

        for (let i = 0; i < 7; i++) {
            createSideSeats(200 - size, 30 + yCoord * (8 + i), 8, 1, (18 - i), 1, 2);
        }
        createSideSeats(200 + 9 * size, 30 + yCoord * 2, 24, 1, 24, 10, 0);
        createSideSeats(200 + 9 * size, 30 + yCoord * 3, 24, 1, 24, 12, 1);
        createSideSeats(200 + 9 * size, 30 + yCoord * 4, 24, 1, 24, 13, 1);
        for (let i = 0; i < 3; i++) {
            createSideSeats(200 + 9 * size, 30 + yCoord * (5 + i), 24, 1, 24 - i, 14, 1);
        }
        for (let i = 0; i < 7; i++) {
            createSideSeats(200 + 9 * size, 30 + yCoord * (8 + i), 24, 1, 24 - i, 9, 2);
        }

        // правый верхний
        createSideSeats(200 + 38 * size, 30, 3, 1, 26, 4, 0);
        createSideSeats(200 + 38 * size, 30 + yCoord, 4, 1, 25, 5, 0);
        createSideSeats(200 + 35 * size, 30 + yCoord * 2, 9, 1, 24, 34, 0);
        createSideSeats(200 + 35 * size, 30 + yCoord * 3, 10, 1, 23, 36, 0);
        createSideSeats(200 + 35 * size, 30 + yCoord * 4, 11, 1, 22, 37, 1);

        createSideSeats(200 + 35 * size, 30 + yCoord * 5, 12, 1, 21, 38, 1);
        createSideSeats(200 + 35 * size, 30 + yCoord * 6, 12, 1, 20, 38, 1);
        createSideSeats(200 + 35 * size, 30 + yCoord * 7, 12, 1, 19, 38, 1);
        for (let i = 0; i < 7; i++) {
            createSideSeats(200 + 35 * size, 30 + yCoord * (8 + i), 7, 1, 18 - i, 33, 2);
        }

        // левый нижний
        for (let i = 0; i < 5; i++) {
            createSideSeats(200 - size * 5, 30 + yCoord * (17 + i), 12, 1, 11 - i, 1, 2);
        }
        for (let i = 0; i < 5; i++) {
            createSideSeats(200 - size * 5, 30 + yCoord * 5 + yCoord * (17 + i), 12, 1, 11 - i, 1, 3);
        }
        createSideSeats(200 - size * 4, 30 + yCoord * 27, 11, 1, 1, 1, 4);


        // центральный нижний
        for (let i = 0; i < 10; i++) {
            createSideSeats(200 + 9 * size, 30 + yCoord * (17 + i), 24, 1, (11 - i), 13, 3);
        }
        createSideSeats(200 + 9 * size, 30 + yCoord * 27, 24, 1, 1, 12, 4);

        // правый нижный
        for (let i = 0; i < 5; i++) {
            createSideSeats(200 + 35 * size, 30 + yCoord * (17 + i), 11, 1, 11 - i, 37, 2);
        }

        for (let i = 0; i < 5; i++) {
            createSideSeats(200 + 35 * size, 30 + yCoord * (22 + i), 7, 1, 6 - i, 37, 3);
        }
        createSideSeats(200 + 35 * size, 30 + yCoord * 27, 7, 1, 1, 36, 4);

    }


    function createSideSeats(x, y, miniRectangleWidth, miniRectangleHeight, row, col, price) {
        for (let i = 0; i < miniRectangleWidth; i += 1) {
            for (let j = 0; j < miniRectangleHeight; j += 15) {
                createSeat(x + i * 15, y + 20 + j, row, col++, price);

            }
        }
    }

    const tooltip = document.getElementById('tooltip');
    // for (let i = 1; i <= 26; i++) {
    //     const rowNumber = document.createElementNS("http://www.w3.org/2000/svg", 'text');
    //     rowNumber.setAttribute('x', 20);
    //     rowNumber.setAttribute('y', 58 + i * 25);
    //     rowNumber.setAttribute('fill', 'white');
    //     rowNumber.setAttribute('font-size', '12');
    //     rowNumber.setAttribute('font-family', "Arial, sans-serif");
    //     rowNumber.textContent = 'Ряд ' + i;
    //     cinemaHall.appendChild(rowNumber);
    // }
    const yPos = 25;
    for (i = 0; i < 15; i++) {
        createRowNum(70, 85 + yPos * i, 26 - i);
    }


    for (j = 0; j < 11; j++) {
        createRowNum(70, 85 + 17 * yPos + yPos * j, 11 - j);
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
    // stageLabel.setAttribute('width', '800');
    // stageLabel.setAttribute('height', '50');
    stageLabel.setAttribute('fill', 'white');
    stageLabel.setAttribute('text-anchor', 'middle');
    stageLabel.setAttribute('font-size', '20');
    stageLabel.textContent = 'Сцена';
    cinemaHall.appendChild(stageLabel);


    function createSeat(x, y, row, col, price) {
        const seat = document.createElementNS("http://www.w3.org/2000/svg", 'circle');

        seat.setAttribute('cx', x);
        seat.setAttribute('cy', y + 30);
        seat.setAttribute('r', '7');
        seat.setAttribute('fill', 'gray');
        seat.setAttribute('data-coords', row + " " + col);
        seat.setAttribute('data-row', row);
        seat.setAttribute('data-col', col);
        seat.setAttribute('data-text', "");
        seat.setAttribute('price', priceSeats[price]);
        if (!reservedSeatsData.includes(row + " " + col)) {
            seat.setAttribute('fill', COLORS[price]);
        } else {
            seat.setAttribute('fill', 'grey');
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


                const mouseX = event.target.getAttribute("cx");
                const mouseY = event.target.getAttribute("cy");

                tooltip.setAttribute('x', mouseX - 70);
                tooltip.setAttribute('y', mouseY - 70);
                tooltip.setAttribute('fill', 'grey');
                text.setAttribute('x', mouseX + 20);
                text.setAttribute('y', mouseY - 30);
                tspan1.setAttribute('x', mouseX - 25);

                tspan1.setAttribute('y', mouseY - 70);

                tspan2.setAttribute('x', mouseX);
                tspan2.textContent = event.target.getAttribute("data-row") + " ряд, " + event.target.getAttribute("data-col") + ' место';
                tspan1.textContent = event.target.getAttribute("price") + ' ₽';
                event.target.setAttribute('r', '11');
                event.target.setAttribute('fill', '#483D8B');
                cinemaHall.appendChild(tooltip);
                cinemaHall.appendChild(text);
            }
        });

        seat.addEventListener('mouseout', function (event) {
            if (!reservedSeatsData.includes(event.target.getAttribute("data-coords"))) {
                event.target.setAttribute('r', '7');
                event.target.setAttribute('fill', COLORS[price]);
                tooltip.textContent = '';
                tooltip.remove();
                text.remove();
            }
        });

        seat.addEventListener('click', function (event) {
            const seatCoords = event.target.getAttribute("data-coords");

            if (!reservedSeatsData.includes(seatCoords)) {
                const isSeatSelected = event.target.classList.contains('selected');
                if (isSeatSelected) {
                    const index = selectedSeats.indexOf(seatCoords);
                    if (index !== -1) {
                        selectedSeats.splice(index, 1);
                    }
                    event.target.classList.toggle('selected');
                    console.log('Seat deselected');
                } else {
                    console.log('da');
                    selectedSeats.push(seatCoords);
                    console.log('Seat selected');
                    event.target.classList.toggle('selected');
                    document.getElementById('selectedSeats').value = selectedSeats.join(',');
                    console.log(selectedSeats);

                }
            }
        });
    }

});