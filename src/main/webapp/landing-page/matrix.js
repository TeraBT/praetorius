const canvas = document.getElementById('background');
const ctx = canvas.getContext('2d');

var w = canvas.width = canvas.clientWidth;
var h = canvas.height = canvas.clientHeight;
var cols = Math.floor(w / 20) + 1;
var ypos = Array(cols).fill(0);

function matrix () {
  if (w != canvas.clientWidth || h != canvas.clientHeight) {
    w = canvas.width = canvas.clientWidth;
    h = canvas.height = canvas.clientHeight;
    cols = Math.floor(w / 20) + 1;
    ypos = Array(cols).fill(0);
  }
  ctx.fillStyle = '#000';
  ctx.fillRect(0, 0, w, h);
  
  ctx.fillStyle ='#555555';
  ctx.font = '6pt NDS12';
  
  ypos.forEach((y, ind) => {
    const text = String.fromCharCode(Math.random() * 128);
    const x = ind * 20;
    ctx.fillText(text, x, y);
    if (y > 100 + Math.random() * 10000) ypos[ind] = 0;
    else ypos[ind] = y + 20;
  });
}

setInterval(matrix, 50);