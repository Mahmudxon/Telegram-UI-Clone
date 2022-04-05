package uz.uniconsoft.messanger.presentation.ui.intro

import android.content.Intent
import android.graphics.SurfaceTexture
import android.graphics.drawable.BitmapDrawable
import android.opengl.GLES20
import android.opengl.GLUtils
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.TextureView
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder
import dagger.hilt.android.AndroidEntryPoint
import uz.uniconsoft.messanger.R
import uz.uniconsoft.messanger.business.domain.util.Device
import uz.uniconsoft.messanger.business.domain.util.DispatchQueue
import uz.uniconsoft.messanger.business.domain.util.TAG
import uz.uniconsoft.messanger.business.domain.util.getDeviceType
import uz.uniconsoft.messanger.presentation.component.Intro
import uz.uniconsoft.messanger.presentation.theme.Blue200
import uz.uniconsoft.messanger.presentation.theme.Blue500
import uz.uniconsoft.messanger.presentation.theme.Theme
import uz.uniconsoft.messanger.presentation.theme.ThemeManger
import uz.uniconsoft.messanger.presentation.ui.auth.AuthActivity
import javax.inject.Inject
import javax.microedition.khronos.egl.*
import javax.microedition.khronos.opengles.GL
import javax.microedition.khronos.opengles.GL10

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@AndroidEntryPoint
@ExperimentalPagerApi
class IntroActivity : AppCompatActivity() {

    @Inject
    lateinit var themeManger: ThemeManger

    private var currentViewPagerPage = 0

    private var currentDate: Long = 0

    private var eglThread: EGLThread? = null

    private val titles by lazy {
        arrayOf(
            getString(R.string.Page1Title),
            getString(R.string.Page2Title),
            getString(R.string.Page3Title),
            getString(R.string.Page5Title),
            getString(R.string.Page4Title),
            getString(R.string.Page6Title)
        )
    }
    private val messages by lazy {
        arrayOf(
            getString(R.string.Page1Message),
            getString(R.string.Page2Message),
            getString(R.string.Page3Message),
            getString(R.string.Page5Message),
            getString(R.string.Page4Message),
            getString(R.string.Page6Message)
        )
    }

    private val icons by lazy {
        arrayOf(
            R.drawable.intro1,
            R.drawable.intro2,
            R.drawable.intro3,
            R.drawable.intro4,
            R.drawable.intro5,
            R.drawable.intro6,
            R.drawable.intro7,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val defTheme = if (isSystemInDarkTheme()) Theme.defDark else Theme.defLight
            themeManger.changeTheme(defTheme)
            val theme = themeManger.currentTheme.value
            val pagerState = rememberPagerState()
            val boxModifier = if (getDeviceType() == Device.Type.Tablet)
                Modifier
                    .height(500.dp)
                    .width(500.dp)
                    .background(Color.White)
            else
                Modifier
                    .fillMaxSize()
                    .background(Color.White)

            Box(Modifier.fillMaxSize()) {

                Image(
                    painter = painterResource(id = theme.chatBackground),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxSize()
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = boxModifier
                    )
                    {

                        IntroPagerView(
                            pagerState = pagerState,
                            messages = titles.toList(),
                            description = messages.toList()
                        )
                        Column(modifier = Modifier.fillMaxSize())
                        {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(bottom = 16.dp, top = 16.dp),
                                verticalArrangement = Arrangement.Bottom,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Texture(
                                    modifier = Modifier
                                        .width(200.dp)
                                        .height(150.dp),
                                    pagerState = pagerState
                                )



                                currentViewPagerPage = pagerState.currentPage
                                Intro.setScrollOffset(pagerState.currentPageOffset)
                            }
                            Column(
                                modifier = Modifier
                                    .weight(2f)
                                    .fillMaxWidth()
                                    .padding(
                                        start = 40.dp,
                                        top = 80.dp,
                                        bottom = 40.dp,
                                        end = 40.dp
                                    ),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                HorizontalPagerIndicator(
                                    pagerState = pagerState,
                                    modifier = Modifier
                                        .align(Alignment.CenterHorizontally)
                                        .padding(16.dp),
                                    activeColor = Blue200,
                                    inactiveColor = Color.Gray
                                )

                                Spacer(modifier = Modifier.weight(1f))

                                Button(
                                    onClick = {
                                        startActivity(
                                            Intent(
                                                this@IntroActivity,
                                                AuthActivity::class.java
                                            )
                                        )
                                        finish()
                                    }, modifier = Modifier
                                        .height(40.dp)
                                        .padding(0.dp)
                                        .fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                                    contentPadding = PaddingValues(0.dp)
                                )
                                {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(RoundedCornerShape(5.dp)),
                                        contentAlignment = Alignment.Center
                                    )
                                    {

                                        Spacer(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .placeholder(
                                                    visible = true,
                                                    highlight = PlaceholderHighlight.shimmer(
                                                        progressForMaxAlpha = 0.2f,
                                                        animationSpec = infiniteRepeatable(
                                                            animation = tween(
                                                                durationMillis = 2000,
                                                                delayMillis = 1000,
                                                                easing = FastOutSlowInEasing
                                                            )
                                                        )
                                                    ), color = Blue500
                                                )
                                        )

                                        Text(
                                            text = stringResource(id = R.string.StartMessaging).uppercase(),
                                            textAlign = TextAlign.Center,
                                            style = TextStyle(
                                                color = Color.White,
                                                fontSize = 16.sp
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }

                }
            }

        }
    }


    inner class EGLThread(private val surfaceTexture: SurfaceTexture) : DispatchQueue("EGLThread") {
        private var egl10: EGL10? = null
        private var eglDisplay: EGLDisplay? = null
        private var eglConfig: EGLConfig? = null
        private var eglContext: EGLContext? = null
        private var eglSurface: EGLSurface? = null
        private var gl: GL? = null
        private var initied = false
        private val textures = IntArray(23)
        private val EGL_CONTEXT_CLIENT_VERSION = 0x3098
        private val EGL_OPENGL_ES2_BIT = 4
        private val lastRenderCallTime: Long = 0

        private fun initGL(): Boolean {
            egl10 = EGLContext.getEGL() as EGL10
            eglDisplay = egl10!!.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY)
            if (eglDisplay === EGL10.EGL_NO_DISPLAY) {
                Log.e(TAG, "initGL: ${GLUtils.getEGLErrorString(egl10!!.eglGetError())}")
                finish()
                return false
            }

            val version = IntArray(2)
            if (!egl10!!.eglInitialize(eglDisplay, version)) {
                Log.e(TAG, "initGL: ${GLUtils.getEGLErrorString(egl10!!.eglGetError())}")
                finish()
                return false
            }

            val configsCount = IntArray(1)
            val configs = arrayOfNulls<EGLConfig>(1)
            val configSpec = intArrayOf(
                EGL10.EGL_RENDERABLE_TYPE, EGL_OPENGL_ES2_BIT,
                EGL10.EGL_RED_SIZE, 8,
                EGL10.EGL_GREEN_SIZE, 8,
                EGL10.EGL_BLUE_SIZE, 8,
                EGL10.EGL_ALPHA_SIZE, 8,
                EGL10.EGL_DEPTH_SIZE, 24,
                EGL10.EGL_STENCIL_SIZE, 0,
                EGL10.EGL_SAMPLE_BUFFERS, 1,
                EGL10.EGL_SAMPLES, 2,
                EGL10.EGL_NONE
            )
            eglConfig =
                if (!egl10!!.eglChooseConfig(eglDisplay, configSpec, configs, 1, configsCount)) {
                    Log.e(TAG, "initGL: ${GLUtils.getEGLErrorString(egl10!!.eglGetError())}")
                    finish()
                    return false
                } else if (configsCount[0] > 0) {
                    configs[0]
                } else {
                    Log.e(TAG, "initGL: ${GLUtils.getEGLErrorString(egl10!!.eglGetError())}")
                    finish()
                    return false
                }

            val attrib_list = intArrayOf(
                EGL_CONTEXT_CLIENT_VERSION,
                2,
                EGL10.EGL_NONE
            )
            eglContext =
                egl10!!.eglCreateContext(eglDisplay, eglConfig, EGL10.EGL_NO_CONTEXT, attrib_list)
            if (eglContext == null) {
                Log.e(TAG, "initGL: ${GLUtils.getEGLErrorString(egl10!!.eglGetError())}")
                finish()
                return false
            }

            eglSurface = if (surfaceTexture is SurfaceTexture) {
                egl10!!.eglCreateWindowSurface(eglDisplay, eglConfig, surfaceTexture, null)
            } else {
                finish()
                return false
            }

            if (eglSurface == null || eglSurface === EGL10.EGL_NO_SURFACE) {
                Log.e(TAG, "initGL: ${GLUtils.getEGLErrorString(egl10!!.eglGetError())}")
                finish()
                return false
            }
            if (!egl10!!.eglMakeCurrent(eglDisplay, eglSurface, eglSurface, eglContext)) {
                Log.e(TAG, "initGL: ${GLUtils.getEGLErrorString(egl10!!.eglGetError())}")
                finish()
                return false
            }
            gl = eglContext!!.gl

            GLES20.glGenTextures(23, textures, 0)
            loadTexture(R.drawable.intro_fast_arrow_shadow, 0)
            loadTexture(R.drawable.intro_fast_arrow, 1)
            loadTexture(R.drawable.intro_fast_body, 2)
            loadTexture(R.drawable.intro_fast_spiral, 3)
            loadTexture(R.drawable.intro_ic_bubble_dot, 4)
            loadTexture(R.drawable.intro_ic_bubble, 5)
            loadTexture(R.drawable.intro_ic_cam_lens, 6)
            loadTexture(R.drawable.intro_ic_cam, 7)
            loadTexture(R.drawable.intro_ic_pencil, 8)
            loadTexture(R.drawable.intro_ic_pin, 9)
            loadTexture(R.drawable.intro_ic_smile_eye, 10)
            loadTexture(R.drawable.intro_ic_smile, 11)
            loadTexture(R.drawable.intro_ic_videocam, 12)
            loadTexture(R.drawable.intro_knot_down, 13)
            loadTexture(R.drawable.intro_knot_up, 14)
            loadTexture(R.drawable.intro_powerful_infinity_white, 15)
            loadTexture(R.drawable.intro_powerful_infinity, 16)
            loadTexture(R.drawable.intro_powerful_mask, 17)
            loadTexture(R.drawable.intro_powerful_star, 18)
            loadTexture(R.drawable.intro_private_door, 19)
            loadTexture(R.drawable.intro_private_screw, 20)
            loadTexture(R.drawable.intro_tg_plane, 21)
            loadTexture(R.drawable.intro_tg_sphere, 22)

            Intro.setTelegramTextures(textures[22], textures[21])
            Intro.setPowerfulTextures(textures[17], textures[18], textures[16], textures[15])
            Intro.setPrivateTextures(textures[19], textures[20])
            Intro.setFreeTextures(textures[14], textures[13])
            Intro.setFastTextures(textures[2], textures[3], textures[1], textures[0])
            Intro.setIcTextures(
                textures[4],
                textures[5],
                textures[6],
                textures[7],
                textures[8], textures[9], textures[10], textures[11], textures[12]
            )
            Intro.onSurfaceCreated()
            currentDate = System.currentTimeMillis() - 1000

            return true
        }

        fun finish() {
            if (eglSurface != null) {
                egl10!!.eglMakeCurrent(
                    eglDisplay,
                    EGL10.EGL_NO_SURFACE,
                    EGL10.EGL_NO_SURFACE,
                    EGL10.EGL_NO_CONTEXT
                )
                egl10!!.eglDestroySurface(eglDisplay, eglSurface)
                eglSurface = null
            }
            if (eglContext != null) {
                egl10!!.eglDestroyContext(eglDisplay, eglContext)
                eglContext = null
            }
            if (eglDisplay != null) {
                egl10!!.eglTerminate(eglDisplay)
                eglDisplay = null
            }
        }

        val drawRunnable: Runnable = object : Runnable {
            override fun run() {
                if (!initied) {
                    return
                }
                if (eglContext != egl10!!.eglGetCurrentContext() || eglSurface != egl10!!.eglGetCurrentSurface(
                        EGL10.EGL_DRAW
                    )
                ) {
                    if (!egl10!!.eglMakeCurrent(eglDisplay, eglSurface, eglSurface, eglContext)) {
                        Log.e(TAG, "initGL: ${GLUtils.getEGLErrorString(egl10!!.eglGetError())}")
                        return
                    }
                }
                val time: Float = (System.currentTimeMillis() - currentDate) / 1000.0f
                Intro.setPage(currentViewPagerPage)
                Intro.setDate(time)
                Intro.onDrawFrame()
                egl10!!.eglSwapBuffers(eglDisplay, eglSurface)
                postRunnable({ this.run() }, 16)
            }
        }


        private fun loadTexture(resId: Int, index: Int) {
            val drawable =
                ContextCompat.getDrawable(this@IntroActivity, resId)
            if (drawable is BitmapDrawable) {
                val bitmap = drawable.bitmap
                GLES20.glBindTexture(GL10.GL_TEXTURE_2D, textures[index])
                GLES20.glTexParameteri(
                    GL10.GL_TEXTURE_2D,
                    GL10.GL_TEXTURE_MIN_FILTER,
                    GL10.GL_LINEAR
                )
                GLES20.glTexParameteri(
                    GL10.GL_TEXTURE_2D,
                    GL10.GL_TEXTURE_MAG_FILTER,
                    GL10.GL_LINEAR
                )
                GLES20.glTexParameteri(
                    GL10.GL_TEXTURE_2D,
                    GL10.GL_TEXTURE_WRAP_S,
                    GL10.GL_CLAMP_TO_EDGE
                )
                GLES20.glTexParameteri(
                    GL10.GL_TEXTURE_2D,
                    GL10.GL_TEXTURE_WRAP_T,
                    GL10.GL_CLAMP_TO_EDGE
                )
                GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0)
            }
        }

        fun shutdown() {
            postRunnable {
                finish()
                val looper = Looper.myLooper()
                looper?.quit()
            }
        }

        fun setSurfaceTextureSize(width: Int, height: Int) {
            Intro.onSurfaceChanged(width, height, Math.min(width / 150.0f, height / 150.0f), 0)
        }

        override fun run() {
            initied = initGL()
            super.run()
        }
    }

    @Composable
    fun Texture(modifier: Modifier, pagerState: PagerState) {
        AndroidView(modifier = modifier, factory = { context ->
            val textureView = TextureView(context)
            textureView.surfaceTextureListener = object : TextureView.SurfaceTextureListener {
                override fun onSurfaceTextureAvailable(
                    surface: SurfaceTexture,
                    width: Int,
                    height: Int
                ) {
                    if (eglThread == null) {
                        eglThread = EGLThread(surface)
                        eglThread?.setSurfaceTextureSize(width, height)
                        eglThread?.postRunnable { eglThread?.drawRunnable?.run() }
                    }
                }

                override fun onSurfaceTextureSizeChanged(
                    surface: SurfaceTexture,
                    width: Int,
                    height: Int
                ) {
                    if (eglThread != null) {
                        eglThread!!.setSurfaceTextureSize(width, height)
                    }
                }

                override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                    if (eglThread != null) {
                        eglThread!!.shutdown()
                        eglThread = null
                    }
                    return true
                }

                override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {

                }

            }

            textureView
        })

    }
}
