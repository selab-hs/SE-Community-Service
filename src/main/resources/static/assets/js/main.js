/*
	Future Imperfect by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
*/
function handleCommonError(response) {
	if (response.status === 401) {
		alert("[ " + response.status + " ] " + JSON.parse(response.responseText).message);
		window.localStorage.clear();
		location.href = "http://localhost:8080/login";
	} else {
		alert("[ " + response.status + " ] " + JSON.parse(response.responseText).message);
		location.href = "http://localhost:8080/boards";
	}
}


(function($) {

	var imageElements = document.querySelectorAll(".board-img1");
	imageElements.forEach(function(image) {
		image.src = "/images/notice2.png";
	});

	var	$window = $(window),
		$body = $('body'),
		$menu = $('#menu'),
		$sidebar = $('#sidebar'),
		$main = $('#main');

	// Breakpoints.
		breakpoints({
			xlarge:   [ '1281px',  '1680px' ],
			large:    [ '981px',   '1280px' ],
			medium:   [ '737px',   '980px'  ],
			small:    [ '481px',   '736px'  ],
			xsmall:   [ null,      '480px'  ]
		});

	// Play initial animations on page load.
		$window.on('load', function() {
			window.setTimeout(function() {
				$body.removeClass('is-preload');
			}, 100);
		});

	// Menu.
		$menu
			.appendTo($body)
			.panel({
				delay: 500,
				hideOnClick: true,
				hideOnSwipe: true,
				resetScroll: true,
				resetForms: true,
				side: 'right',
				target: $body,
				visibleClass: 'is-menu-visible'
			});

	// Search (header).
		var $search = $('#search'),
			$search_input = $search.find('input');

		$body
			.on('click', '[href="#search"]', function(event) {

				event.preventDefault();

				// Not visible?
					if (!$search.hasClass('visible')) {

						// Reset form.
							$search[0].reset();

						// Show.
							$search.addClass('visible');

						// Focus input.
							$search_input.focus();

					}

			});

		$search_input
			.on('keydown', function(event) {

				if (event.keyCode == 27)
					$search_input.blur();

			})
			.on('blur', function() {
				window.setTimeout(function() {
					$search.removeClass('visible');
				}, 100);
			});

	// Intro.
		var $intro = $('#intro');

		// Move to main on <=large, back to sidebar on >large.
			breakpoints.on('<=large', function() {
				$intro.prependTo($main);
			});

			breakpoints.on('>large', function() {
				$intro.prependTo($sidebar);
			});

})(jQuery);

function clear_localStorage() {
	window.localStorage.clear();
	location.reload();
}

$(document).ready(function() {
	// 로컬 스토리지에서 토큰 가져오기
	const token = localStorage.getItem('X-SELAB-AUTH-TOKEN');

	if (token) {
		// 토큰이 있을 때
		$("#login_button").hide(); // Log In 버튼 숨기기
		$("#sign_up_button").hide(); // Log In 버튼 숨기기
		$("#logout_button").show(); // Log Out 버튼 표시
		$("#edit_button").show();   // Edit 버튼 표시
	} else {
		// 토큰이 없을 때
		$("#login_button").show(); // Log In 버튼 숨기기
		$("#sign_up_button").show(); // Log In 버튼 숨기기
		$("#logout_button").hide(); // Log Out 버튼 표시
		$("#edit_button").hide();   // Edit 버튼 표시
	}

	$.ajax({
		url: 'http://localhost:8080/api/v1/boards/category',
		method: 'GET',
		contentType: 'application/json',
		success: function (result) {
			const ul = $('.links ul');
			const ul2 = $('section .links');

			result.data.forEach(function (item) {
				const li = document.createElement('li');
				const a = document.createElement('a');
				a.setAttribute('href', 'http://localhost:8080/boards/' + item.id);
				a.textContent = item.name;
				li.append(a);
				ul.append(li);

				const li2 = document.createElement('li');
				const a2 = document.createElement('a');
				a2.setAttribute('href', 'http://localhost:8080/boards/' + item.id);
				const h3 = document.createElement('h3');
				h3.textContent = item.name;
				const p = document.createElement('p');
				p.textContent = item.description;

				a2.append(h3);
				a2.append(p);
				li2.append(a2);
				ul2.append(li2);
			});
		},
		error: function(response) {
			handleCommonError(response);
		}
	});
});