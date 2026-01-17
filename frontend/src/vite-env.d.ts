/// <reference types="vite/client" />

// CSS Module declarations
declare module '*.css' {
  const content: { [className: string]: string };
  export default content;
}

// Allow side-effect CSS imports
declare module '*.css?inline' {
  const content: string;
  export default content;
}
