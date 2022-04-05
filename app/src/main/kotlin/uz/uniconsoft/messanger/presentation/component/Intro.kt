package uz.uniconsoft.messanger.presentation.component

object Intro {
    external fun onDrawFrame()
    external fun setScrollOffset(a_offset: Float)
    external fun setPage(page: Int)
    external fun setDate(a: Float)
    external fun setIcTextures(
        a_ic_bubble_dot: Int,
        a_ic_bubble: Int,
        a_ic_cam_lens: Int,
        a_ic_cam: Int,
        a_ic_pencil: Int,
        a_ic_pin: Int,
        a_ic_smile_eye: Int,
        a_ic_smile: Int,
        a_ic_videocam: Int
    )

    external fun setTelegramTextures(a_telegram_sphere: Int, a_telegram_plane: Int)
    external fun setFastTextures(
        a_fast_body: Int,
        a_fast_spiral: Int,
        a_fast_arrow: Int,
        a_fast_arrow_shadow: Int
    )

    external fun setFreeTextures(a_knot_up: Int, a_knot_down: Int)
    external fun setPowerfulTextures(
        a_powerful_mask: Int,
        a_powerful_star: Int,
        a_powerful_infinity: Int,
        a_powerful_infinity_white: Int
    )

    external fun setPrivateTextures(a_private_door: Int, a_private_screw: Int)
    external fun onSurfaceCreated()
    external fun onSurfaceChanged(a_width_px: Int, a_height_px: Int, a_scale_factor: Float, a1: Int)
}
