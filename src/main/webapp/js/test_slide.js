$(function() {
	function toggleChangeBtn() {
		var slideIndex = $('.test').index($('.active'));
		$('.change_btn').show();
		if (slideIndex == 0) {
			$('.prev').addClass('inactive');
			$('.next').removeClass('inactive');
		} else if (slideIndex == $('.test').length - 1) {
			$('.next').addClass('inactive');
			$('.prev').removeClass('inactive');
		} else {
			$('.prev').removeClass('inactive');
			$('.next').removeClass('inactive');
		}
	}
	$('.change_btn').click(function() {
		var $displaySlide = $('.active');
		$displaySlide.removeClass('active');
		if ($(this).hasClass('next')) {
			$displaySlide.next().addClass('active');
		} else {
			$displaySlide.prev().addClass('active');
		}
		toggleChangeBtn();
	});
});
