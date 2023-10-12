/*
	Future Imperfect by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
*/

(function($) {

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
});