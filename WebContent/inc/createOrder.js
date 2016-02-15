function show(elementId) {
	document.getElementById(elementId).className = '';
}

function hide(elementId) {
	document.getElementById(elementId).className = 'hidden';
}

function toggle(eltIdToShow, eltIdToHide) {
	show(eltIdToShow);
	hide(eltIdToHide);
}
