$(function () {
	'use strict';
	// "品目の追加"ボタンを押した場合の処理
	$('.btn_add').click(function () {
		var i, new_btn, len_list, new_list;
		// 品目入力欄を追加
		len_list = $('.answer_list > li').length;
		new_list = '<li><div class="answer"><input type="text" name="answers" placeholder="答えを入力してください"></div><button type="button" class="btn_remove">削除</button></li>';
		$('.answer_list').append(new_list);
	});
	
	// 削除ボタンを押した場合の処理
	$(document).on('click', '.answer_list .btn_remove', function (ev) {
		var i, idx, len_list;
		// 品目入力欄を削除
		idx = $(ev.target).parent().index();
		$('.answer_list > li').eq(idx).remove();
		len_list = $('.answer_list > li').length;
		// 入力欄がひとつになるなら、削除ボタンは不要なので消去
		if (len_list === 1) {
			$('.answer_list .btn_remove').remove();
		}
	});
});
